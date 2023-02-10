package readCSV;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * formats different input dates to LocalDate
 */
public class DateFormatter {
    public static final DateTimeFormatter[] FORMATTERS = new DateTimeFormatter[] {
            DateTimeFormatter.BASIC_ISO_DATE,
            DateTimeFormatter.ofPattern("EEE, d MMM yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("M-d-yy"),
            DateTimeFormatter.ofPattern("dd-MM-uuuu", Locale.ENGLISH),
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ISO_DATE_TIME,
            DateTimeFormatter.ISO_INSTANT
    };

    public static LocalDate formatDate(String dateInString) {
        int i = 0;
        return formatDateRec(i, dateInString);
    }

    public static LocalDate formatDateRec(int i, String dateInString) {
        if (i >= FORMATTERS.length) {
            return null;
        }
        try {
            return LocalDate.parse(dateInString, FORMATTERS[i]);
        } catch (DateTimeParseException exception) {
            return formatDateRec(++i, dateInString);
        }
    }
}
