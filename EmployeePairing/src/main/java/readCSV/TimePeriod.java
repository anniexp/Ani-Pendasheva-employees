package readCSV;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TimePeriod {
    public static Output outputData = new Output();
    /**
     * Fills up the output data structure, which includes all of the pairs of employees who have worked
     * together on common projects
     * @param projectList list of all records
     */
    public static void getIntercectedPeriods(ArrayList<Project> projectList) {
        for (int i = 0; i < projectList.size(); i++) {
            Project p1 = projectList.get(i);
            for (int j = i + 1; j < projectList.size(); j++) {
                Project p2 = projectList.get(j);
                LocalDate p1From = p1.getDateFrom();
                LocalDate p2From = p2.getDateFrom();
                LocalDate p1To = p1.getDateTo();
                LocalDate p2To = p2.getDateTo();

                if (p1.getProjectID() != p2.getProjectID()) {
                    continue;
                }
                if (p1From.isAfter(p2From) && p1To.isBefore(p2To)) {
                    long record = p1From.until(p1To, ChronoUnit.DAYS);
                    outputData.addToDataM(p1, p2.getEmpID(), record);
                    continue;
                }

                if (p2From.isAfter(p1From) && p2To.isBefore(p1To)) {
                    long record = p2From.until(p2To, ChronoUnit.DAYS);
                    outputData.addToDataM(p1, p2.getEmpID(), record);
                    continue;
                }

                if (p1From.isAfter(p2From) && p1To.isAfter(p2To)) {
                    long record = p1From.until(p2To, ChronoUnit.DAYS);
                    if (record > 0) {
                        outputData.addToDataM(p1, p2.getEmpID(), record);
                        continue;
                    }
                }
                if (p2From.isAfter(p1From) && p2.getDateTo().isAfter(p1To)) {
                    long record = p2From.until(p1To, ChronoUnit.DAYS);
                    if (record > 0) {
                        outputData.addToDataM(p1, p2.getEmpID(), record);
                    }
                }
            }
        }
        Output.print(getSumWorkingDaysByPair());
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
                if (outputData.data[i][0].equals(outputData.data[j][0]) &&
                        outputData.data[i][1].equals(outputData.data[j][1])) {
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
}
