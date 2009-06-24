package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;

import shm.common.Utils;
import shm.model.Member;
import shm.model.OkDialy;
import shm.model.OkDialyMeta;

public class UpdateController extends OkDialyController {
    private OkDialyMeta ok = new OkDialyMeta();

    @Override
    public Navigation runInTx() {

        Member member = getLoginMemberFromSession();
        Date okDialyDate = Utils.toDate(requestScope("okDialyDate"));
        OkDialy okDialy =
            from(ok)
                .where(ok.member.eq(member), ok.dialyDate.eq(okDialyDate))
                .getSingleResult();
        
        List<String> items = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            String key = "item_" + i;
            String item = requestScope(key);
            items.add(item);
        }
        okDialy.setItems(items);
        pm.makePersistent(okDialy);
        
        return null;
    }
}
