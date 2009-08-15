package shm.controller.login;

import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

public class LoginController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    @Override
    public Navigation run() {
        if (!validate()) {
            return forward("index");
        }
        
        return redirect("/");
    }
    
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("memberId", v.required());
        v.add("password", v.required(), v.minlength(8));
        return v.validate();
    }
}
