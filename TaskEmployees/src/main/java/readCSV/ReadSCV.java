package readCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static readCSV.View.file;

public class ReadSCV {
    public static List<Record> fileToOjects(File file) throws Exception {
        Scanner sc = new Scanner(file);
        List<Record> res = new ArrayList<>();

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] ch1 = s.split(", ", 4);
            Record record = new Record(Integer.parseInt(ch1[0]), Integer.parseInt(ch1[1]), ch1[2], ch1[3]);
            res.add(record);
        }
        sc.close();

        return res;
    }

    public static void loadFile() throws Exception {
        List<Record> records;
        records = ReadSCV.fileToOjects(file);
        TimePeriod.calcIntercectedPeriods((ArrayList<Record>) records);
    }
}
