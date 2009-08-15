package shm.controller.login;

import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import shm.common.user.UserServiceUtil;

public class IndexController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(IndexController.class.getName());

    @Override
    public Navigation run() {
        String loginUrl = UserServiceUtil.getUserService().createLoginURL("/");
        requestScope("loginUrl", loginUrl);
        return forward("index.jsp");
    }
}
