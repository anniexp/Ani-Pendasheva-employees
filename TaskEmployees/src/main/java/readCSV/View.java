package readCSV;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static readCSV.ReadSCV.loadFile;
import static readCSV.TimePeriod.outputDataOfPair;

public class View extends JPanel implements ActionListener {
    public static File file;
    JTable j;
    JButton openButton;
    JFileChooser fc;
    JTextArea log;

    /**
     * Panel Constructor
     */
    public View() {
        super(new BorderLayout());

        String[] columnNames = {"Employee ID #1", "Employee ID #2", "Record ID", "Days worked"};
        j = new JTable(outputDataOfPair.data, columnNames);
        j.setBounds(30, 40, 200, 300);

        JPanel tableTitlePanel = new JPanel();
        tableTitlePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "All collaborations of employees in each project",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        JScrollPane sp = new JScrollPane(j);

        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);

        fc = new JFileChooser(new File("..\\"));
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.add(sp, BorderLayout.CENTER);
        this.add(tableTitlePanel, BorderLayout.AFTER_LAST_LINE);
        this.add(log, BorderLayout.AFTER_LAST_LINE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(View.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                log.append("File is loaded. Please click on table rows to show results!");
                try {
                    loadFile();
                } catch (Exception ex) {
                    log.append("Error occurred.");
                    ex.printStackTrace();
                }

            } else {
                log.append("Open command cancelled.");
            }
        }
    }

    /**
     * Initialises the ui frame
     */
    static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new View());
        frame.pack();
        frame.setVisible(true);
    }
}
