package shm.controller.okdialy;

import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.common.MyJDOController;
import shm.controller.member.LoginController;
import shm.dao.MemberDao;
import shm.model.Member;
import shm.model.OkDialy;

public class ListController extends MyJDOController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(ListController.class.getName());

    @Override
    public Navigation runInTx() {
        MemberDao memberDao = new MemberDao(pm);
        String memberId = sessionScope(LoginController.LOGIN_MEMBER_KEY);
        Member m = memberDao.getMemberByMemberId(memberId);
        
        List<OkDialy> okDialyList = m.getOkDialyList();
        requestScope("okDialyList", copy(okDialyList));
        return forwardBase("list.jsp");
    }
}
