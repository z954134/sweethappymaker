package shm.controller.signup;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import shm.common.MyController;
import shm.common.user.UserServiceUtil;
import shm.dao.MemberDao;
import shm.model.Member;

import com.google.appengine.api.users.User;

/**
 * Google User用のID登録
 * @author Tsuyoshi
 *
 */
public class RegisterIdController extends MyController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(RegisterIdController.class.getName());

    
    private MemberDao dao = new MemberDao();
    
    @Override
    public Navigation runInTx() {
        if (!validate()) {
            return forward("gaccount.jsp");
        }
        
        Member member = new Member();
        member.setMemberId(asString("memberId"));
        User user = UserServiceUtil.getCurrentUser();
        if (user == null) {
            // ユーザが無い場合（通常ありえない）
            String url = UserServiceUtil.getUserService().createLoginURL("/login/GoogleLogin");
            return redirect(url);
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
