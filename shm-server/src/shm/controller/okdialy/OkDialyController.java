package shm.controller.okdialy;

import shm.common.Const;
import shm.common.MyJDOController;
import shm.common.SystemException;
import shm.model.Member;

public class OkDialyController extends MyJDOController {

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
