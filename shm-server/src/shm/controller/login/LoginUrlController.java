package shm.controller.login;

import java.util.logging.Logger;
import org.slim3.controller.Navigation;

import shm.cool.common.MyController;
import shm.cool.common.user.UserServiceUtil;

public class LoginUrlController extends MyController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LoginUrlController.class.getName());

    @Override
    public Navigation run() {
        String loginUrl = UserServiceUtil.getUserService().createLoginURL("/");
        requestScope("loginUrl", loginUrl);
        return forward("loginUrl.jsp");
    }
}
