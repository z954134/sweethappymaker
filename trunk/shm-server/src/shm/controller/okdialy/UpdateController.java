package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Navigation;

import shm.common.Const;
import shm.model.Member;
import shm.model.OkDialy;

public class UpdateController extends OkDialyController {
    @Override
    public Navigation runInTx() {

        Member member = getLoginMemberFromSession();
        Date okDialyDate = asDate("okDialyDate", Const.DATE_FORMAT);
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
