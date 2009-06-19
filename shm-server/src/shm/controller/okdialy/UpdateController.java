package shm.controller.okdialy;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class UpdateController extends Controller {

    @Override
    public Navigation run() {
        return redirect(basePath);
    }
}
