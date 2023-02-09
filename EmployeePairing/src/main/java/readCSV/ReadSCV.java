package readCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static readCSV.View.file;

public class ReadSCV {
    public static List<Project> fileToOjects(File file) throws Exception {
        Scanner sc = new Scanner(file);
        List<Project> res = new ArrayList<>();

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] ch1 = s.split(", ", 4);
            Project project = new Project(Integer.parseInt(ch1[0]), Integer.parseInt(ch1[1]), ch1[2], ch1[3]);
            res.add(project);
        }
        sc.close();

        return res;
    }

    public static void loadFile() throws Exception {
        String pathname = "C:\\Users\\user\\Desktop\\task\\input.csv";
        List<Project> projects;
        try {
            projects = ReadSCV.fileToOjects(file);
        } catch (NullPointerException exception) {

          //  file = new File(pathname);
           // projects = ReadSCV.fileToOjects(file);
            exception.getMessage();
        }
        projects = ReadSCV.fileToOjects(file);
        TimePeriod.getIntercectedPeriods((ArrayList<Project>) projects);
    }
}
