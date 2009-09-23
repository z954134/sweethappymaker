package shm.controller.member;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import shm.controller.MyController;

public class LogoutUrlController extends MyController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LogoutUrlController.class.getName());

    @Override
    public Navigation run() {
        UserService userService = UserServiceFactory.getUserService();
        String loginUrl = userService.createLogoutURL("/");
        requestScope("loginUrl", loginUrl);
        return forward("logoutUrl.jsp");
    }
}
