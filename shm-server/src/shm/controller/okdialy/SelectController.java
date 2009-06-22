package shm.controller.okdialy;

import java.util.Date;

import org.slim3.controller.Navigation;
import org.slim3.util.DateUtil;

import shm.common.Const;
import shm.model.Member;
import shm.model.OkDialy;
import shm.model.OkDialyMeta;

public class SelectController extends OkDialyController {

    @Override
    public Navigation run() {
        OkDialyMeta ok = new OkDialyMeta();

        Member member = getLoginMemberFromSession();
        Date dialyDate =
            DateUtil.toDate(
                requestScope("dialyDate").toString(),
                Const.DATE_FORMAT);

        tx.begin();
        OkDialy dialy =
            from(ok)
                .where(ok.member.eq(member), ok.dialyDate.eq(dialyDate))
                .getSingleResult();

        requestScope("okDialy", toBeanMap(dialy));
        return forwardBase("select.jsp");
    }
}
