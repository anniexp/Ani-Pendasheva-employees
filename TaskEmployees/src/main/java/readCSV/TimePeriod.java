package readCSV;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Construction of the final output data
 */
public class TimePeriod {
    public static Output outputData = new Output();
    public static Output outputDataOfPair = new Output();

    /**
     * Fills up the output data structure, which includes all the pairs of employees who have worked
     * together on common projects
     *
     * @param recordList list of all records
     */
    public static Output calcIntercectedPeriods(ArrayList<Record> recordList) {
        for (int i = 0; i < recordList.size(); i++) {
            Record p1 = recordList.get(i);
            for (int j = i + 1; j < recordList.size(); j++) {
                Record p2 = recordList.get(j);
                LocalDate p1From = p1.getDateFrom();
                LocalDate p2From = p2.getDateFrom();
                LocalDate p1To = p1.getDateTo();
                LocalDate p2To = p2.getDateTo();

                if (p1.getProjectID() != p2.getProjectID()) {
                    continue;
                }
                if (p1From.isAfter(p2From) && p1To.isBefore(p2To)) {
                    long record = p1From.until(p1To, ChronoUnit.DAYS);
                    outputData.addToDataM(p1.getProjectID(), p1.getEmpID(), p2.getEmpID(), record);
                    continue;
                }

                if (p2From.isAfter(p1From) && p2To.isBefore(p1To)) {
                    long record = p2From.until(p2To, ChronoUnit.DAYS);
                    outputData.addToDataM(p1.getProjectID(), p1.getEmpID(), p2.getEmpID(), record);
                    continue;
                }
                if (p1From.isAfter(p2From) && p1To.isAfter(p2To)) {
                    long record = p1From.until(p2To, ChronoUnit.DAYS);
                    if (record > 0) {
                        outputData.addToDataM(p1.getProjectID(), p1.getEmpID(), p2.getEmpID(), record);
                        continue;
                    }
                }
                if (p2From.isAfter(p1From) && p2.getDateTo().isAfter(p1To)) {
                    long record = p2From.until(p1To, ChronoUnit.DAYS);
                    if (record > 0) {
                        outputData.addToDataM(p1.getProjectID(), p1.getEmpID(), p2.getEmpID(), record);
                    }
                }
            }
        }
        Map.Entry<String, Integer> entry = getSumWorkingDaysByPair();
        Output.print(entry);
        setPairPeriods(entry.getKey());

        return outputData;
    }

    /**
     * This methods identifies the pair of employees who have worked
     * together on common projects for the longest period of time.
     * The method calculates the sum of all working days by pair of employees, who have worked together.
     * Then with the stream api it gets the biggest value.
     *
     * @return the entry with the biggest days value hashmap with key pairs concatenated id's and value sum of working days
     */
    public static Map.Entry<String, Integer> getSumWorkingDaysByPair() {
        Map<String, Integer> res = new HashMap<>();

        for (int i = 0; i < outputData.getLastFilled(); i++) {
            for (int j = i + 1; j <= outputData.data[i].length; j++) {
                String key = outputData.data[i][0] + outputData.data[i][1];
                if ((outputData.data[i][0].equals(outputData.data[j][0]) &&
                        outputData.data[i][1].equals(outputData.data[j][1])) ||
                        (outputData.data[i][1].equals(outputData.data[j][0]) &&
                         outputData.data[i][0].equals(outputData.data[j][1]))
                ) {
                    res.putIfAbsent(key, 0);
                    res.put(key, Integer.parseInt(outputData.data[i][3]) +
                            Integer.parseInt(outputData.data[j][3]));
                } else {
                    res.putIfAbsent(key, Integer.parseInt(outputData.data[i][3]));
                }
            }
        }

        return res.entrySet().stream().max(Map.Entry.comparingByValue()).get();
    }

    /**
     * returns all common projects of the pair
     *
     * @param key - the pair employees concatenated ids, also the key of the pair of employees that have worked together for the longest time
     */
    public static int setPairPeriods(String key) {
        String emp1 = key.substring(0, 3);
        String emp2 = key.substring(3, 6);
        int record = 0;
        for (int i = 0; i < outputData.getLastFilled(); i++) {
            if ((emp1.equals(outputData.data[i][0]) && emp2.equals(outputData.data[i][1])
            )) {
                int prId = Integer.parseInt(outputData.data[i][2]);
                int emp1ID = Integer.parseInt(outputData.data[i][0]);
                int emp2ID = Integer.parseInt(outputData.data[i][1]);
                record = Integer.parseInt(outputData.data[i][3]);
                outputDataOfPair.addToDataM(prId, emp1ID, emp2ID, record);
            }
        }
        return record;
    }
}
