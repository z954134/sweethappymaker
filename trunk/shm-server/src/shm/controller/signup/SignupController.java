package shm.controller.signup;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import shm.dao.MemberDao;
import shm.model.Member;

public class SignupController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(SignupController.class.getName());

    private MemberDao dao = new MemberDao();
    
    @Override
    public Navigation run() {
        if (!validate()) {
            return forward("index");
        }
        
        Member m = new Member();
        m.setMemberId(asString("memberId"));
        m.setPassword(asString("password"));

        dao.makePersistent(m);
        return forward("signup.jsp");
    }
    
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("memberId", v.required(), new MemberIdValidator());
        v.add("password", v.required(), v.minlength(8));
        return v.validate();
    }
    

}
