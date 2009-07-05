package shm.controller.okdialy;

import java.util.Date;
import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.common.Utils;
import shm.common.user.UserServiceUtil;
import shm.model.OkDialy;

import com.google.appengine.api.users.User;

public class DeleteController extends OkDialyController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(DeleteController.class.getName());

    @Override
    public Navigation runInTx() {
        User user = UserServiceUtil.getCurrentUser();
        Date dialyDate = Utils.toDate(requestScope("okDialyDate"));
        OkDialy okDialy = okDialyDao.find(user, dialyDate);
        okDialyDao.deletePersistent(okDialy);
        return null;
    }
}
