package shm.dao;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import shm.common.Utils;
import shm.meta.OkDialyMeta;
import shm.model.Member;
import shm.model.OkDialy;

public class OkDialyDao extends MyGenericDao<OkDialy> {

    private OkDialyMeta ok = new OkDialyMeta();

    public OkDialyDao() {
        super(OkDialy.class);
    }
    
    public OkDialyDao(PersistenceManager pm) {
        super(OkDialy.class, pm);
    }

    public OkDialy find(Member member, Date dialyDate) {
        OkDialy okDialy =
            from()
                .where(ok.member.eq(member), ok.dialyDate.eq(dialyDate))
                .getSingleResult();
        return okDialy;
    }

    public List<OkDialy> findAll(Member member) {
        Utils.notNull(member, "member");
        List<OkDialy> list = from().where(ok.member.eq(member)).getResultList();
        return list;
    }

    public List<OkDialy> findByMonth(Member member, Date month) {
        Date firstDayOfMonth = Utils.getFirstDayOfMonth(month);
        Date lastDayOfMonth = Utils.getLastDayOfMonth(month);
        return findByRange(member, firstDayOfMonth, lastDayOfMonth);
    }
    
    public List<OkDialy> findByRange(Member member, Date from, Date to) {
        checkRange(from, to);
        
        List<OkDialy> list =
            from().where(
                ok.member.eq(member),
                ok.dialyDate.ge(from),
                ok.dialyDate.le(to)).getResultList();
        return list;
    }
    
    private void checkRange(Date from, Date to) {
        if (from.compareTo(to) > 0) {
            String msg = "開始日より終了日のほうが未来日です。" +
            		"from =[" + from + "]" +
            		"to =[" + to + "]";
            throw new IllegalArgumentException(msg);
        }
    }
}
