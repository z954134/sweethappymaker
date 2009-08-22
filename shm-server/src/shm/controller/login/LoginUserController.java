package shm.controller.login;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.common.MyController;

public class LoginUserController extends MyController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LoginUserController.class.getName());

    @Override
    public Navigation run() {
        requestScope("loginUser" , sessionScope("memberId"));
        return forward("loginUser.jsp");
    }
}
