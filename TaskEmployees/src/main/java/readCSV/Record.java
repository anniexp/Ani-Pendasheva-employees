package readCSV;

import java.time.LocalDate;
/**
 * Cvs file lines as objects
 */
public class Record {
    private final int empID;
    private final int ProjectID;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public Record(int empID, int projectID, String dateFrom, String dateTo) {
        this.empID = empID;
        this.ProjectID = projectID;
        this.dateFrom = DateFormatter.formatDate(dateFrom);
        if (dateTo.equals("NULL")) {
            dateTo = LocalDate.now().toString();
        }
        this.dateTo = DateFormatter.formatDate(dateTo);
    }

    @Override
    public String toString() {
        return "Record{" +
                "empID=" + empID +
                ", ProjectID=" + ProjectID +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }

    public int getEmpID() {
        return empID;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
