package shm.controller.signup;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class GaccountController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(GaccountController.class.getName());

    @Override
    public Navigation run() {
        return forward("gaccount.jsp");
    }
}
