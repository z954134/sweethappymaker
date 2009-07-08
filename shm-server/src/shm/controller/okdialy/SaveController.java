package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import shm.common.Const;
import shm.common.user.UserServiceUtil;
import shm.model.OkDialy;

import com.google.appengine.api.users.User;


public class SaveController extends OkDialyController {

    @Override
    public Navigation runInTx() {
        
        User user = UserServiceUtil.getCurrentUser();
        
        Date dialyDate = asDate("okDialyDate", Const.DATE_FORMAT);
        OkDialy okDialy = okDialyDao.find(user, dialyDate);
        if (okDialy == null) {
            save(user, dialyDate);
        } else {
            update(okDialy, dialyDate);
        }
        return null;
    }

    private void update(OkDialy okDialy,Date dialyDate) {
        List<String> items = createItemsFromRequest();
        okDialy.setDialyDate(dialyDate);
        okDialy.setItems(items);
    }

    private void save(User user, Date dialyDate) {
        List<String> items = createItemsFromRequest();
        if (items.isEmpty()) {
            return;
        }
        
        OkDialy okDialy = new OkDialy();
        okDialy.setDialyDate(dialyDate);
        okDialy.setItems(items);
        okDialy.setUser(user);
        okDialyDao.makePersistent(okDialy);
    }
    
    private List<String> createItemsFromRequest() {
        String[] param = paramValues("item");
        if (param == null) {
            return new ArrayList<String>(0);
        }
        
        List<String> items = new ArrayList<String>(10);
        boolean empty = true;
        for (String item : param) { 
            if (!StringUtil.isEmpty(item)) {
                empty = false;
            }
            items.add(item);
        }
        return empty ? new ArrayList<String>(0) : items;
    }
}
