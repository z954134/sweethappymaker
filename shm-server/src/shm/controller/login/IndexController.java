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
        

        return forward("index.jsp");
    }
    

}
