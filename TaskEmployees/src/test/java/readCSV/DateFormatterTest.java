package readCSV;

import org.junit.Assert;

import java.time.LocalDate;

public class DateFormatterTest {

    @org.junit.Test
    public void formatDate() {
        Assert.assertEquals(LocalDate.parse("2009-01-02"),DateFormatter.formatDate("2009-01-02"));
        Assert.assertEquals(LocalDate.parse("2009-01-02"),DateFormatter.formatDate("01-02-2009"));
        Assert.assertEquals(LocalDate.parse("2020-02-10"),DateFormatter.formatDate("2020-02-10T22:55:13-08:00"));
        Assert.assertEquals(LocalDate.parse("2023-01-02"),DateFormatter.formatDate("1-2-23"));
        Assert.assertEquals(LocalDate.parse("2009-01-02"),DateFormatter.formatDate("20090102"));
        Assert.assertEquals(LocalDate.parse("2011-12-03"),DateFormatter.formatDate("2011-12-03"));
        Assert.assertEquals(LocalDate.parse("2011-12-03"),DateFormatter.formatDate("2011-12-03T10:15:30Z"));
    }
}