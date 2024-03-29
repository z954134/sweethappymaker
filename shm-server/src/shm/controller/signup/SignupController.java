package shm.controller.signup;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import shm.cool.common.Const;
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
        // ログイン成功
        sessionScope("memberId", m.getMemberId());
        return redirect(Const.APP_MAIN_URL);
    }
    
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("memberId", v.required(), new MemberIdValidator(dao));
        v.add("password", v.required(), v.minlength(8));
        return v.validate();
    }
    

}
