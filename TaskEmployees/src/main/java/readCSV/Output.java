package readCSV;

import java.util.Map;

public class Output {
    String[][] data;
    private int lastFilled;

    public Output() {
        this.data = new String[10][20];
        this.lastFilled = 0;
    }

    public int getLastFilled() {
        return lastFilled;
    }

    /**
     * Task Requirement for basic output
     *
     * @param - entry - the pair of employees who have worked
     *          together on common projects for the longest period of time.
     */
    public static void print(Map.Entry entry) {
        String s = entry.getKey().toString();
        String emp1 = s.substring(0, 3);
        String emp2 = s.substring(3, 6);
        String days = entry.getValue().toString();
        System.out.println(emp1 + ", " + emp2 + ", " + days);
    }

    /**
     * converts object of Record to String[] and adds it to data matrix, to be used to fill the table in the ui
     */
    public void addToDataM(int pId, int emplId1, int emplId2, long days) {
        String prId = pId + "";
        String emp1 = emplId1 + "";
        String emp2 = emplId2 + "";
        String daysTogether = days + "";
        String[] row = new String[]{emp1, emp2, prId, daysTogether};
        data[lastFilled++] = row;
    }
}
