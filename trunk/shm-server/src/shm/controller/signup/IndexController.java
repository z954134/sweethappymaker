package shm.controller.signup;

import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import shm.cool.common.user.UserServiceUtil;

public class IndexController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(IndexController.class.getName());

    @Override
    public Navigation run() {
        String signupUrl = UserServiceUtil.getUserService().createLoginURL("/signup/gaccount");
        requestScope("signupUrl", signupUrl);

        return forward("index.jsp");
    }
}
