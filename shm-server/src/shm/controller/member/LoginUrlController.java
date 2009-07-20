package shm.controller.member;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginUrlController extends MemberController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LoginUrlController.class.getName());

    
    public Navigation run() {
        UserService userService = UserServiceFactory.getUserService();
        String loginUrl = userService.createLoginURL("/");
        requestScope("loginUrl", loginUrl);
        return forward("loginUrl.jsp");
    }
//    @Override
//    public Navigation run() {
//    
//        String loginUrl = buildLoginUrl();
//        requestScope("loginUrl", loginUrl);
//        return forward("loginUrl.jsp");
//    }
//    
//    private String buildLoginUrl() {
//        StringBuilder url = new StringBuilder();
//        url.append("https://");
//        url.append(request.getServerName());
//        url.append(":");
//        url.append(request.getServerPort());
//        url.append(basePath);
//        url.append("login");
//        return url.toString();
//    }
}
