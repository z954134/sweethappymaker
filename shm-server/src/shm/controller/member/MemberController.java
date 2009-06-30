package shm.controller.member;

import shm.common.Const;
import shm.common.MyController;
import shm.common.SystemException;
import shm.dao.MemberDao;
import shm.model.Member;

public abstract class MemberController extends MyController {
    protected MemberDao memberDao = new MemberDao();
    protected final Member getLoginMemberFromSession() {
        String memberId = sessionScope(Const.LOGIN_MEMBER_ID);
        if (memberId == null) {
            memberId = "guest";
        }
        Member member = memberDao.getMemberByMemberId(memberId);
        if (member == null) {
            String msg = "該当するメンバーが存在しません。メンバーID = [" + memberId + "]";
            throw new SystemException(msg);
        }
        return member;
    }
}
