package readCSV;

public class Output {
    String[][] data;
    private int lastFilled;

    public Output() {
        this.data = new String[4][10];
        this.lastFilled = 0;
    }

    public static void print(Project p1, int emplId2, long days) {
        System.out.println(" Employee ID #1 " + p1.getEmpID() +
                " Employee ID #2 " + emplId2 +
                " Project ID " + p1.getProjectID() +
                " Days worked " + days);
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
