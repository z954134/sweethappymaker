package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;

import shm.common.Const;
import shm.common.user.UserServiceUtil;
import shm.model.OkDialy;

import com.google.appengine.api.users.User;


public class SaveController extends OkDialyController {

    @Override
    public Navigation runInTx() {
        
        User user = UserServiceUtil.getCurrentUser();
        
        Date dialyDate = asDate("okDialyDate", Const.DATE_FORMAT);
        OkDialy okDialy = okDialyDao.select(user, dialyDate);
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
        OkDialy okDialy;
        okDialy = new OkDialy();
        okDialy.setDialyDate(dialyDate);
        okDialy.setItems(items);
        okDialy.setUser(user);
        okDialyDao.makePersistent(okDialy);
    }
    
    private List<String> createItemsFromRequest() {
        List<String> items = new ArrayList<String>(10);
        for (int i = 1; i <= 10; i++) {
            String key = "item_" + i;
            String item = requestScope(key);
            if (item == null) {
                item = "";
            }
            items.add(item);
        }
        return items;
    }
}
