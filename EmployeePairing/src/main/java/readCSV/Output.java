package readCSV;

import javax.swing.*;

public class Output {
    JFrame f;
    JTable j;

    public Output() {
        f = new JFrame();
        f.setTitle("Schedule");
        String[][] data = {
                {"Kundan Kumar Jha", "4031", "CSE"},
                {"Anand Jha", "6014", "IT"}
        };
        String[] columnNames = {"Employee ID #1", "Employee ID #2", "Project ID", "Days worked"};
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        f.setSize(600, 300);
        f.setVisible(true);
    }

    public static void print(Project p1, int emplId2, long days) {
       System.out.println(" Employee ID #1 " + p1.getEmpID() +
               " Employee ID #2 " + emplId2 +
               " Project ID " + p1.getProjectID() +
               " Days worked " + days);
    }
}
