package readCSV;

import javax.swing.*;
import java.io.File;

public class View {
    JFrame f;
    JTable j;

    public View() {
        f = new JFrame();
        f.setTitle("Schedule");
        String[][] data = {
                {"Kundan Kumar Jha", "4031", "CSE","fwfwf"},
                {"Anand Jha", "6014", "IT", "fwsfwf"}
        };
        // Column Names
        String[] columnNames = {"Employee ID #1", "Employee ID #2", "Project ID", "Days worked"};
        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);


        /*JFileChooser j = new JFileChooser(new File("C:\\Users\\user\\Desktop\\task"));
        // Open the save dialog
        j.showSaveDialog(null);*/


    }
}
