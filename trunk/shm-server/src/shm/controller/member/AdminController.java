package shm.controller.member;

import java.util.logging.Logger;
import org.slim3.controller.JDOController;
import org.slim3.controller.Navigation;

public class AdminController extends JDOController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    @Override
    public Navigation run() {
        return redirect(basePath);
    }
}
