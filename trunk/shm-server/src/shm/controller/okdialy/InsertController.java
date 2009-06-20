package shm.controller.okdialy;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import shm.model.Member;
import shm.model.OkDialy;

public class InsertController extends OkDialyController {

    @Override
    public Navigation runInTx() {

        OkDialy okDialy = new OkDialy();
        BeanUtil.copy(request, okDialy);
        
        Member member = getLoginMemberFromSession();
        member.addOkDialy(okDialy);
        
        pm.makePersistent(member);

        return null;
    }
}
