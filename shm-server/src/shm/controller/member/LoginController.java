package shm.controller.member;

import java.security.Principal;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginController extends Controller {

    @Override
    public Navigation run() {
        
        UserService userService = UserServiceFactory.getUserService();
        String thisURL = request.getRequestURI();
        
        Principal p = request.getUserPrincipal();
        if (p == null) {
            requestScope("loginUrl", userService.createLoginURL(thisURL));
            return forward("login.jsp");
        }
        
        User user = userService.getCurrentUser();
        requestScope("userName", p.getName());
        requestScope("isAdmin", userService.isUserAdmin());
        requestScope("isLoggedIn", userService.isUserLoggedIn());
        requestScope("domain", user.getAuthDomain());
        requestScope("email", user.getEmail());
        requestScope("nickname", user.getNickname());
        sessionScope("isLoggedIn", new Boolean(true));
        return forward("loggedin.jsp");

    }
}
