package shm.controller.member;

import shm.common.MyController;
import shm.common.SecurityViolationException;
import shm.common.user.UserServiceUtil;
import shm.dao.MemberDao;
import shm.model.Member;

import com.google.appengine.api.users.User;

public abstract class MemberController extends MyController {

    public static final String MEMBER_ID_KEY = "memberId";

    protected MemberDao memberDao = new MemberDao();

    protected final String getLoginMemberId() {
        String memberId = sessionScope(MEMBER_ID_KEY);
        return memberId;
    }
    
    protected final String getMemberIdInRequest() {
        return requestScope(MEMBER_ID_KEY);
    }
    
    private final String getMemberIdInSession() {
        return sessionScope(MEMBER_ID_KEY);
    }
    protected final User getUser() {
        User user = UserServiceUtil.getUserService().getCurrentUser();
        return user;
    }
    
    protected final Member getMember() {
        String memberId = getMemberIdInSession();
        if (memberId == null) {
            throw new SecurityViolationException("ログインされていません。");
        }
        return memberDao.findMember(memberId);
    }
}
