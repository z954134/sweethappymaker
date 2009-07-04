package shm.controller.login;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.common.MyController;
import shm.common.user.UserServiceUtil;

import com.google.appengine.api.users.User;

public class LoginUserController extends MyController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LoginUserController.class.getName());

    @Override
    public Navigation run() {
        User user = UserServiceUtil.getCurrentUser();
        requestScope("loginUser", user.getNickname());
        return forwardBase("loginUser.jsp");
    }
}
