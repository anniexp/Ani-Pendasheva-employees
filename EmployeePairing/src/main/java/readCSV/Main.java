package readCSV;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
      String pathname = "C:\\Users\\user\\Desktop\\task\\input.csv";
        List<Project> projects = ReadSCV.fileToOjects(pathname);
        /*for (Project p : projects) {
            System.out.println(p);
        }*/
        TimePeriod.getIntercectedPeriods((ArrayList<Project>) projects);
        new View();
    }
}
