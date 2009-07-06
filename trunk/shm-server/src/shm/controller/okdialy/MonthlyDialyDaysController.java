package shm.controller.okdialy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.common.Utils;
import shm.common.user.UserServiceUtil;
import shm.model.OkDialy;

import com.google.appengine.api.users.User;

public class MonthlyDialyDaysController extends OkDialyController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(MonthlyDialyDaysController.class.getName());

    @Override
    public Navigation run() {
        begin();
        int y = asInteger("year");
        int m = asInteger("month");
        Date d = format(y, m);
        
        User user = UserServiceUtil.getCurrentUser();
        List<OkDialy> dialyList = okDialyDao.findByMonth(user, d);
        List<String> days = new ArrayList<String>(dialyList.size());
        for (OkDialy e: dialyList) {
            days.add(e.getDialyDateText());
        }
        requestScope("monthlyDialyDays", days);
        return forward("monthlyDialyDays.jsp");
    }
    
    private Date format(int y, int m) {
        String s = y + "/" + new DecimalFormat("00").format(m + 1) + "/01" ;
        Date d = Utils.toDate(s);
        return d;
    }
}
