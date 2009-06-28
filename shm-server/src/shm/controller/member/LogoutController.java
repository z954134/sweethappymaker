package shm.controller.member;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import shm.common.Const;

public class LogoutController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LogoutController.class.getName());

    @Override
    public Navigation run() {
        removeSessionScope(Const.LOGIN_MEMBER_ID);        
        return null;
    }
}
