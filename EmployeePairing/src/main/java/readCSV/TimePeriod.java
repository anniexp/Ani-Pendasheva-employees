package readCSV;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TimePeriod {
   public static Output outputData = new Output();

    public static Set<Long> getIntercectedPeriods(ArrayList<Project> projectList) {
        Set<Long> res = new HashSet<>();
       // Output outputData = new Output();

        for (int i = 0; i < projectList.size(); i++) {
            Project p1 = projectList.get(i);
            for (int j = i + 1; j < projectList.size(); j++) {
                Project p2 = projectList.get(j);
                LocalDate p1From = p1.getDateFrom();
                LocalDate p2From = p2.getDateFrom();
                LocalDate p1To = p1.getDateTo();
                LocalDate p2To = p2.getDateTo();

                if (p1.getProjectID() == p2.getProjectID()) {
                    if (p1From.isAfter(p2From) && p1To.isBefore(p2To)) {
                        long record = p1From.until(p1To, ChronoUnit.DAYS);
                      //  res.add(record);
                       // Output.print(p1, p2.getEmpID(), record);
                        outputData.addToDataM(p1, p2.getEmpID(), record);
                        continue;
                    }

                    if (p2From.isAfter(p1From) && p2To.isBefore(p1To)) {
                        long record = p2From.until(p2To, ChronoUnit.DAYS);
                       // res.add(record);
                       // Output.print(p1, p2.getEmpID(), record);
                        outputData.addToDataM(p1, p2.getEmpID(), record);
                        continue;
                    }

                    if (p1From.isAfter(p2From) && p1To.isAfter(p2To)) {
                        long record = p1From.until(p2To, ChronoUnit.DAYS);
                        if (record > 0) {
                          //  res.add(record);
                           // Output.print(p1, p2.getEmpID(), record);
                            outputData.addToDataM(p1, p2.getEmpID(), record);
                            continue;
                        }
                    }
                    if (p2From.isAfter(p1From) && p2.getDateTo().isAfter(p1To)) {
                        long record = p2From.until(p1To, ChronoUnit.DAYS);
                        if (record > 0) {
                          //  res.add(record);
                          //  Output.print(p1, p2.getEmpID(), record);
                            outputData.addToDataM(p1, p2.getEmpID(), record);
                        }
                    }
                }
            }
        }

        return res;
    }


    /**
     * get longest collaboration periods for each project
     * @param projects - list of all collaboration periods
     * @return filtered list, only one record for each project
     */
    public static Set<Long> getLongestInPByProject(HashSet<Project> projects) {
        Set<Long> res = new HashSet<>();
      /* Collections.max(projects, new Comparator<Project>() {
           @Override
           public int compare(Project o1, Project o2) {
               return Integer.compare();
           }
       });*/

        return res;
    }

}
