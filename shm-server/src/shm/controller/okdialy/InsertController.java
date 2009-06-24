package shm.controller.okdialy;

import org.slim3.controller.Navigation;

import shm.common.Utils;
import shm.model.Member;
import shm.model.OkDialy;

public class InsertController extends OkDialyController {

    @Override
    public Navigation runInTx() {

        OkDialy okDialy = new OkDialy();
        okDialy.setDialyDate(Utils.toDate(requestScope("okDialyDate")));
        
        for (int i = 1;i <= 10; i++) {
            String key = "item_" + i;
            String item = requestScope(key);
            okDialy.addItem(item);
        }
        
        Member member = getLoginMemberFromSession();
        member.addOkDialy(okDialy);
        
        pm.makePersistent(member);

        return null;
    }
}
