package readCSV;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class TimePeriodTest {
    ArrayList<Record> input;

    @Before
    public void init() {
        input = new ArrayList<>();
        input.add(new Record(143, 10, "2009-01-01", "2011-04-27"));
        input.add(new Record(219, 10, "2009-01-02", "2009-01-03"));
        input.add(new Record(220, 12, "2009-02-01", "2021-04-27"));
        input.add(new Record(143, 12, "2013-11-01", "2014-01-05"));
        input.add(new Record(220, 10, "2009-01-03", "2009-01-05"));

    }

    @Test()
    public void calcIntercectedPeriods() {
        Output output = TimePeriod.calcIntercectedPeriods(input);
        Output expected = new Output();
        expected.data = new String[][]{
                {"143", "219", "10", "1"},
                {"143", "220", "10", "2"},
                {"220", "143", "12", "65"}
        };
        Assert.assertEquals(expected.data[0], output.data[0]);
        assertArrayEquals(expected.data[1], output.data[1]);
        assertArrayEquals(expected.data[2], output.data[2]);
    }

    @Test
    public void getSumWorkingDaysByPair() {
        Map.Entry<String, Integer> output = TimePeriod.getSumWorkingDaysByPair();
        Assert.assertTrue("143220".equals(output.getKey()) || "220143".equals(output.getKey()));
        Assert.assertEquals(67, output.getValue().intValue());
    }

    @Test
    public void setPairPeriods() {
        int res = TimePeriod.setPairPeriods("143219");
        Assert.assertEquals(1, res);
        Assert.assertEquals(65, TimePeriod.setPairPeriods("220143"));
    }
}