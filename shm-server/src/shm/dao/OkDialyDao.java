package shm.dao;

import java.util.Date;

import shm.model.Member;
import shm.model.OkDialy;
import shm.model.OkDialyMeta;

public class OkDialyDao extends MyGenericDao<OkDialy> {

    private OkDialyMeta ok = new OkDialyMeta();
    
    public OkDialyDao() {
        super(OkDialy.class);
    }

    public OkDialy select(Member member, Date dialyDate) {
        OkDialy okDialy =
            from()
                .where(ok.member.eq(member), ok.dialyDate.eq(dialyDate))
                .getSingleResult();
        return okDialy;
    }
}
