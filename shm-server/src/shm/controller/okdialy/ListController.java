package shm.controller.okdialy;

import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.common.user.UserServiceUtil;
import shm.model.OkDialy;

import com.google.appengine.api.users.User;

public class ListController extends OkDialyController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(ListController.class.getName());

    @Override
    public Navigation run() {
        begin();
        User user = UserServiceUtil.getCurrentUser();
        List<OkDialy> okDialyList = okDialyDao.findAll(user);
        
        requestScope("okDialyList", detachAndCopy(okDialyList));
        return forward("list.jsp");
    }
}
