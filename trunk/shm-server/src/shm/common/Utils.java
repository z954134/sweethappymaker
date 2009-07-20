package shm.common;

import static java.util.Calendar.DAY_OF_MONTH;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.slim3.util.DateUtil;

public final class Utils {

    private Utils() {
    }

    public static Date toDate(String dateStr) {
        Date date = DateUtil.toDate(dateStr, Const.DATE_FORMAT);
        return date;
    }
    
    public static Date toDate(Object dateObj) {
        if (dateObj == null) {
            throw new NullPointerException("Argument must not be null.");
        }
        return toDate(dateObj.toString());
    }
    
    public static String toString(Date date) {
        return DateUtil.toString(date, Const.DATE_FORMAT);
    }
    
    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    public static Date getFirstDayOfMonth(Date orig) {
        Calendar c = toCalendar(orig);
        c.set(DAY_OF_MONTH, c.getActualMinimum(DAY_OF_MONTH));
        return c.getTime();
    }
    
    public static Date getLastDayOfMonth(Date orig) {
        Calendar c = toCalendar(orig);
        c.set(DAY_OF_MONTH, c.getActualMaximum(DAY_OF_MONTH));
        return c.getTime();        
    }
    
    public static String convertIfNull(String orig) {
        return orig == null ? "" : orig;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T convertIfNull(T orig) {
        return (T) (orig == null ? new ArrayList<T>(0) : orig);
    }
}
