package shm.controller.signup;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.users.User;

import shm.common.user.UserServiceUtil;
import shm.dao.MemberDao;
import shm.model.Member;

/**
 * Google User用のID登録
 * @author Tsuyoshi
 *
 */
public class RegisterIdController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(RegisterIdController.class.getName());

    
    private MemberDao dao = new MemberDao();
    
    @Override
    public Navigation run() {
        if (!validate()) {
            return forward("index");
        }
        
        Member member = new Member();
        member.setMemberId(asString("memberId"));
        User user = UserServiceUtil.getCurrentUser();
        if (user == null) {
            String url = UserServiceUtil.getUserService().createLoginURL("/");
            redirect(url);
        }
        member.setUser(user);
        dao.makePersistent(member);
        return redirect("signup.jsp");
    }
    
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("memberId", v.required(), new MemberIdValidator());
        return v.validate();
    }
}
