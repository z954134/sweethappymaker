package shm.common;

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
}
