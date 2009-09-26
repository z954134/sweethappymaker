package shm.controller.login;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import shm.cool.common.user.UserServiceUtil;

import com.google.appengine.api.users.UserService;

public class IndexController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(IndexController.class.getName());

    @Override
    public Navigation run() {
        UserService userService = UserServiceUtil.getUserService();
        String loginUrl = userService.createLoginURL("/login/GoogleLogin");
        requestScope("loginUrl", loginUrl);
        
        String signupUrl = userService.createLoginURL("/signup/gaccount");
        requestScope("signupUrl", signupUrl);

        return forward("index.jsp");
    }
    

}
