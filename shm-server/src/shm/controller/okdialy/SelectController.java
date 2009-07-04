package shm.controller.okdialy;

import java.util.Date;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanMap;

import shm.common.Utils;
import shm.common.user.UserServiceUtil;
import shm.model.OkDialy;

import com.google.appengine.api.users.User;

public class SelectController extends OkDialyController {
    
    @Override
    public Navigation run() {
        begin();
        User user = UserServiceUtil.getCurrentUser();
        Date dialyDate = Utils.toDate(requestScope("dialyDate"));

        OkDialy dialy = okDialyDao.select(user, dialyDate);
        if (dialy == null) {
            dialy = new OkDialy();
            dialy.setDialyDate(dialyDate);
            for (int i = 0; i < 10; i++) dialy.getItems().add(i, new String(""));
        }
        BeanMap m = detachAndCopy(dialy);
        requestScope("okDialy", m);
        
        return forwardBase("select.jsp");
    }
}
