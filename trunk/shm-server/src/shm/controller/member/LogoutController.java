package shm.controller.member;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class LogoutController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LogoutController.class.getName());

    @Override
    public Navigation run() {
        resetSession();
        return null;
    }
    
    private void resetSession() {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }   
    }
}
