package shm.common;

import java.util.Calendar;
import java.util.Date;

import org.slim3.util.DateUtil;

import junit.framework.TestCase;

public class UtilsTest extends TestCase {

    public void testToDateString() {
        Date d = Utils.toDate("2010/07/11");
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        assertEquals(2010, c.get(Calendar.YEAR));
        assertEquals(7 - 1, c.get(Calendar.MONTH));
        assertEquals(11, c.get(Calendar.DAY_OF_MONTH));
        
    }

    public void testToDateObject() {
        Object arg = "2010/07/11";
        Date d = Utils.toDate(arg);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        assertEquals(2010, c.get(Calendar.YEAR));
        assertEquals(7 - 1, c.get(Calendar.MONTH));
        assertEquals(11, c.get(Calendar.DAY_OF_MONTH));
    }

    public void testToStringDate() {
        Calendar c = Calendar.getInstance();
        c.set(2009, 6 - 1, 23);
        Date d = c.getTime();
        String s = Utils.toString(d);
        assertEquals("2009/06/23", s);
    }

    public void testToCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(2005, 11 - 1, 1);
        Date d = c.getTime();
        Calendar actual = Utils.toCalendar(d);
        assertEquals(2005, actual.get(Calendar.YEAR));
        assertEquals(11 - 1, actual.get(Calendar.MONTH));
        assertEquals(1, actual.get(Calendar.DAY_OF_MONTH));
        
    }

    public void testGetFirstDayOfMonth() {
        Date orig = DateUtil.toDate("2003-03-24", "yyyy-MM-dd");
        Date actual = Utils.getFirstDayOfMonth(orig);
        assertEquals("2003-03-01", DateUtil.toString(actual));
        
        
        
    }

    public void testGetLastDayOfMonth() {
        Date orig = DateUtil.toDate("2003-03-24", "yyyy-MM-dd");
        Date actual = Utils.getLastDayOfMonth(orig);
        assertEquals("2003-03-31", DateUtil.toString(actual));
    }

}
