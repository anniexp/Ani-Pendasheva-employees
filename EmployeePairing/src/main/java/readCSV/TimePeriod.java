package readCSV;

import java.time.temporal.ChronoUnit;
import java.util.*;

public class TimePeriod {
    public static Set<Long> getIntercectedPeriods(ArrayList<Project> projectList) {
        Set<Long> res = new HashSet<>();
        for (int i = 0; i < projectList.size(); i++) {
            Project p1 = projectList.get(i);
            for (int j = i + 1; j < projectList.size(); j++) {
                Project p2 = projectList.get(j);
                if (p1.getProjectID() == p2.getProjectID()) {
                    if (p1.getDateFrom().isAfter(p2.getDateFrom()) && p1.getDateTo().isBefore(p2.getDateTo())) {
                        long record = p1.getDateFrom().until(p1.getDateTo(), ChronoUnit.DAYS);
                        res.add(record);
                        Output.print(p1, p2.getEmpID(), record);
                        continue;
                    }

                    if (p2.getDateFrom().isAfter(p1.getDateFrom()) && p2.getDateTo().isBefore(p1.getDateTo())) {
                        long record = p2.getDateFrom().until(p2.getDateTo(), ChronoUnit.DAYS);
                        res.add(record);
                        Output.print(p1, p2.getEmpID(), record);
                        continue;
                    }

                    if (p1.getDateFrom().isAfter(p2.getDateFrom()) && p1.getDateTo().isAfter(p2.getDateTo())) {
                        long record = p1.getDateFrom().until(p2.getDateTo(), ChronoUnit.DAYS);
                        if (record > 0) {
                            res.add(record);
                            Output.print(p1, p2.getEmpID(), record);
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
