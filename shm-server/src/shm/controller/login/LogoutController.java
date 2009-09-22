package shm.controller.login;

import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import shm.cool.common.user.UserServiceUtil;

public class LogoutController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LogoutController.class.getName());

    @Override
    public Navigation run() {
        request.getSession().invalidate();
        String logoutUrl = UserServiceUtil.getUserService().createLogoutURL("/login");
        return redirect(logoutUrl);
    }
}
