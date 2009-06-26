package shm.controller.okdialy;

import java.util.Date;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanMap;

import shm.common.Utils;
import shm.model.Member;
import shm.model.OkDialy;

public class SelectController extends OkDialyController {
    
    @Override
    public Navigation run() {

        Member member = getLoginMemberFromSession();
        Date dialyDate = Utils.toDate(requestScope("dialyDate"));

        tx.begin();
        OkDialy dialy =
            from(ok)
                .where(ok.member.eq(member), ok.dialyDate.eq(dialyDate))
                .getSingleResult();
        
        
        if (dialy == null) {
            dialy = new OkDialy();
            dialy.setDialyDate(dialyDate);
            for (int i = 0; i < 10; i++) dialy.getItems().add(i, new String(""));
        }
        BeanMap m = toBeanMap(dialy);
        requestScope("okDialy", m);
        
        return forwardBase("select.jsp");
    }
}
