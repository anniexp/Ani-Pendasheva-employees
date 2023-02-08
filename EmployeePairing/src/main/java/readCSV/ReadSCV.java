package readCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadSCV {
    public static List<Project> fileToOjects(String pathname) throws Exception {
        Scanner sc = new Scanner(new File(pathname));
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
}
