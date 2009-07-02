package shm.controller.member;

import java.security.Principal;
import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.common.MyController;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LogintestController extends MyController {

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(LogintestController.class.getName());

    @Override
    public Navigation run() {
        UserService userService = UserServiceFactory.getUserService();
        String thisURL = request.getRequestURI();
        Principal principal = request.getUserPrincipal();
        String urlLogout = userService.createLogoutURL(thisURL);
        String urlLogin = userService.createLoginURL(thisURL);
        requestScope("logoutUrl", urlLogout);
        requestScope("loginUrl", urlLogin);
        String name = (principal == null ? "" : principal.getName());
        requestScope("name", name);

        User user = userService.getCurrentUser();
        if (user != null) {
            String authDomain = user.getAuthDomain();
            String email = user.getEmail();
            String nickname = user.getNickname();
            requestScope("authDomain", authDomain);
            requestScope("email", email);
            requestScope("nickname", nickname);
        }
        return null;
    }
}
