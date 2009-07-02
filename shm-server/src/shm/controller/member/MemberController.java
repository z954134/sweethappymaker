package shm.controller.member;

import org.slim3.util.StringUtil;

import shm.common.MyController;
import shm.common.SecurityViolationException;
import shm.common.SystemException;
import shm.dao.MemberDao;
import shm.model.Member;

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
    
    protected final Member getLoginMemberFromSession() {
        String memberId = getLoginMemberId();
        if (memberId == null) {
            memberId = GUEST_ACCOUNT;
        }
        Member member = memberDao.getMemberByMemberId(memberId);
        if (member == null) {
            String msg = "該当するメンバーが存在しません。メンバーID = [" + memberId + "]";
            throw new SystemException(msg);
        }
        return member;
    }

    protected final void checkIdentity() {

        if (isSameMemberId()) {
            return; // valid
        } else if (isAdmin()) {
            return; // valid
        } else if (isGuest()) {
            // invalid
            String msg = "ゲストアカウントでは許可されない操作です。";
            throw new SystemException(msg);
        } else {
            // invalid
            String msg =
                "不正なリクエストが発行されました。"
                    + "メンバーIDパラメータが改ざんされた可能性があります。"
                    + "セッション上のメンバーID=["
                    + getLoginMemberId()
                    + "]"
                    + "リクエスト上のメンバーID=["
                    + requestScope(MEMBER_ID_KEY)
                    + "]";
            throw new SystemException(msg);
        }
    }



    protected final boolean isSameMemberId() {
        String memberIdInRequest = getMemberIdInRequest();
        if (StringUtil.isEmpty(memberIdInRequest)) {
            throw new SystemException("");
        }
        String memberIdInSession = getLoginMemberId();
        return memberIdInRequest.equals(memberIdInSession);
    }

    protected final void assertAdminMember() {
        if (!isAdmin()) {
            String msg = ("管理者権限の無いメンバーからアクセスされました。" +
            		"メンバーID=[" + getLoginMemberId() + "]");
            throw new SecurityViolationException(msg);
        }
    }
    
    protected final boolean isAdmin() {
        return ADMIN_ACCOUNT.equals(getLoginMemberId());
    }

    protected final boolean isGuest() {
        return GUEST_ACCOUNT.equals(getLoginMemberId());
    }
}
