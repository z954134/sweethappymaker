package shm.controller.okdialy;

import java.util.Date;

import org.slim3.controller.Navigation;
import org.slim3.util.DateUtil;

import shm.model.Member;
import shm.model.OkDialy;
import shm.model.OkDialyMeta;

public class SelectController extends OkDialyController {

    @Override
    public Navigation run() {
        OkDialyMeta ok = new OkDialyMeta();
        
        Member member = getLoginMemberFromSession();
        Date dialyDate = DateUtil.toDate(requestScope("dialyDate"));

        tx.begin();
        OkDialy dialy =
            from(ok)
                .where(ok.dialyDate.eq(dialyDate))
                .where(ok.member.eq(member))
                .getSingleResult();
        requestScope("okDialy", toBeanMap(dialy));
        return forwardBase("select.jsp");
    }
}
