package shm.controller.signup;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import shm.dao.MemberDao;

public class GaccountController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(GaccountController.class.getName());

    private MemberDao dao = new MemberDao();
    
    @Override
    public Navigation run() {
        if (!validate()) {
            return forward("gaccount.jsp");
        }
        return forward("gaccount.jsp");
    }
    
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("gaccount", new GoogleAccountValidator(dao));
        return v.validate();
    }
}
