package shm.controller.login;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.common.MyController;
import shm.common.user.UserServiceUtil;

public class LogoutUrlController extends MyController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LogoutUrlController.class.getName());

    @Override
    public Navigation run() {
        String logoutUrl = UserServiceUtil.getUserService().createLogoutURL("/");
        requestScope("logoutUrl", logoutUrl);
        return forwardBase("logoutUrl.jsp");
    }
}
