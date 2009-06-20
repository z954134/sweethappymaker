package shm.controller.okdialy;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import shm.common.MyJDOController;
import shm.model.Member;
import shm.model.OkDialy;

public class InsertController extends MyJDOController {

    @Override
    public Navigation runInTx() {

        OkDialy okDialy = new OkDialy();
        BeanUtil.copy(request, okDialy);
        
        String memberKey = sessionScope("memberId");
        Member member = pm.getObjectById(Member.class, memberKey);
        member.addOkDialy(okDialy);

        pm.makePersistent(member);

        return null;
    }
}
