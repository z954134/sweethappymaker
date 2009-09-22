package shm.controller.login;

import java.util.logging.Logger;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import shm.common.user.UserServiceUtil;
import shm.dao.MemberDao;
import shm.model.Member;

import com.google.appengine.api.users.User;

public class GoogleLoginController extends Controller {

    
    
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(GoogleLoginController.class.getName());

    @Override
    public Navigation run() {
        User user = UserServiceUtil.getCurrentUser();
        if (user == null) {
            String loginUrl = UserServiceUtil.getUserService().createLoginURL("/login/GoogleLogin");
            return redirect(loginUrl);
        }
        
        MemberDao dao = new MemberDao();
        Member member = dao.findMember(user);
        if (member == null) {
            requestScope("msg", "メンバー登録されていません。登録をお願いします。");
            return forward("/signup/gaccount");
        }
        sessionScope("memberId", member.getMemberId());
        return redirect("/flex-bin/main.html");
    }
}
