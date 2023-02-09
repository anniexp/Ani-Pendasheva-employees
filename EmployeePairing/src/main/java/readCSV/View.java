package readCSV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static readCSV.ReadSCV.loadFile;
import static readCSV.TimePeriod.outputData;

public class View extends JPanel implements ActionListener {
    public static File file;
    JTable j;
    JButton openButton;
    JFileChooser fc;
    JTextArea log;

    public View() {
        super(new BorderLayout());

        String[] columnNames = {"Employee ID #1", "Employee ID #2", "Project ID", "Days worked"};
        //create panel for table
        j = new JTable(outputData.data, columnNames);
        j.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(j);

        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);

        fc = new JFileChooser(new File("C:\\Users\\user\\Desktop\\task"));
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        //add table and button panels
        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.add(sp, BorderLayout.CENTER);
        //this.add(log);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(View.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
               file = fc.getSelectedFile();
               System.out.println("File is :" + file.getName());
                try {
                    loadFile();
                } catch (Exception ex) {
                    log.append("Error occurred.");
                }

            } else {
                log.append("Open command cancelled.");
            }
        }
    }

    static void createAndShowGUI() {
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new View());
        frame.pack();
        frame.setVisible(true);
    }
}
