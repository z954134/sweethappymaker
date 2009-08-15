package shm.controller.login;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import shm.common.user.UserServiceUtil;
import shm.dao.MemberDao;
import shm.model.Member;

import com.google.appengine.api.users.User;

public class GLoginController extends Controller {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(GLoginController.class.getName());

    @Override
    public Navigation run() {
        User user = UserServiceUtil.getCurrentUser();
        if (user == null) {
            return redirect("");
        }
        
        MemberDao dao = new MemberDao();
        Member member = dao.findMember(user);
        
        sessionScope("memberId", member.getMemberId());
        
        return redirect(basePath);
    }
}
