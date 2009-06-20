package shm.controller.okdialy;

import java.util.Date;

import org.slim3.controller.Navigation;
import org.slim3.util.DateUtil;

import shm.common.MyJDOController;
import shm.controller.member.LoginController;
import shm.dao.MemberDao;
import shm.model.Member;
import shm.model.OkDialy;
import shm.model.OkDialyMeta;

public class SelectController extends MyJDOController {

    @Override
    public Navigation runInTx() {
        Date dialyDate = DateUtil.toDate(requestScope("dialyDate"));
        MemberDao memberDao = new MemberDao(pm);
        String memberId = sessionScope(LoginController.LOGIN_MEMBER_KEY);
        Member m = memberDao.getMemberByMemberId(memberId);

        OkDialyMeta ok = new OkDialyMeta();
        OkDialy dialy =
            from(ok)
                .where(ok.dialyDate.eq(dialyDate))
                .where(ok.member.eq(m))
                .getSingleResult();
        
        requestScope("okDialy", toBeanMap(dialy));
        return forwardBase("select.jsp");
    }
}
