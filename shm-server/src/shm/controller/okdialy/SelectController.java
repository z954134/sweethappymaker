package shm.controller.okdialy;

import java.util.Date;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanMap;

import shm.common.Utils;
import shm.model.Member;
import shm.model.OkDialy;
import shm.model.OkDialyMeta;

public class SelectController extends OkDialyController {
    
    private OkDialyMeta ok = new OkDialyMeta();
    
    @Override
    public Navigation run() {

        Member member = getLoginMemberFromSession();
        Date dialyDate = Utils.toDate(requestScope("okDialyDate"));

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
