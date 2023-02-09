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

    public static void print(Project p1, int emplId2, long days) {
        System.out.println(" Employee ID #1 " + p1.getEmpID() +
                " Employee ID #2 " + emplId2 +
                " Project ID " + p1.getProjectID() +
                " Days worked " + days);
    }

    /**
     * Task Requirement for basic output
     * @param - entry - identifies the pair of employees who have worked
     * together on common projects for the longest period of time.
     */
    public static void print(Map.Entry entry) {
       String s =  entry.getKey().toString();
       String emp1 =  s.substring(0,3);
       String emp2 =  s.substring(3,6);
       String days = entry.getValue().toString();
       System.out.println(emp1 + ", " + emp2 + ", "  + days);
    }


    public void addToDataM(Project p1, int emplId2, long days) {
        String prId = p1.getProjectID() + "";
        String emp1 = p1.getEmpID() + "";
        String emp2 = emplId2 + "";
        String daysTogether = days + "";
        String[] row = new String[]{emp1, emp2, prId, daysTogether};
        data[lastFilled++] = row;
    }
}
