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

        List<String> items = createItemsFromRequest();
        if (items.isEmpty()) {
            return null;
        }
        Date dialyDate = asDate("okDialyDate", Const.DATE_FORMAT);
        OkDialy okDialy = getDialy(dialyDate);
        okDialy.setItems(items);
        okDialyDao.makePersistent(okDialy);

        return null;
    }
    
    private OkDialy getDialy(Date dialyDate) {
        User user = UserServiceUtil.getCurrentUser();
        OkDialy okDialy = okDialyDao.find(user, dialyDate);
        if (okDialy == null) {
            okDialy = new OkDialy();
            okDialy.setUser(user);
            okDialy.setDialyDate(dialyDate);
        }
        return okDialy;
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
