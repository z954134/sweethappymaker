package shm.controller.member;

import shm.common.MyController;
import shm.common.user.UserServiceUtil;
import shm.dao.MemberDao;
import shm.model.Member;

import com.google.appengine.api.users.User;

public abstract class MemberController extends MyController {

    public static final String MEMBER_ID_KEY = "memberId";

    public static final String ADMIN_ACCOUNT = "administrator";

    public static final String GUEST_ACCOUNT = "guest";

    protected MemberDao memberDao = new MemberDao();

    protected final String getLoginMemberId() {
        String memberId = sessionScope(MEMBER_ID_KEY);
        return memberId;
    }
    
    protected final String getMemberIdInRequest() {
        return requestScope(MEMBER_ID_KEY);
    }
    
    protected final User getUser() {
        User user = UserServiceUtil.getUserService().getCurrentUser();
        return user;
    }
    
    protected final Member getMember() {
        User user = getUser();
        if (user == null) {
            throw new IllegalStateException("ログインされていません。");
        }
        return memberDao.findMember(user);
    }
}
