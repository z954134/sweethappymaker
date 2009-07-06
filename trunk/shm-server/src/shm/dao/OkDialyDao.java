package shm.dao;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import shm.common.Utils;
import shm.model.OkDialy;
import shm.model.OkDialyMeta;

import com.google.appengine.api.users.User;

public class OkDialyDao extends MyGenericDao<OkDialy> {

    private OkDialyMeta ok = new OkDialyMeta();

    public OkDialyDao() {
        super(OkDialy.class);
    }
    
    public OkDialyDao(PersistenceManager pm) {
        super(OkDialy.class, pm);
    }

    public OkDialy find(User user, Date dialyDate) {
        OkDialy okDialy =
            from()
                .where(ok.user.eq(user), ok.dialyDate.eq(dialyDate))
                .getSingleResult();
        return okDialy;
    }

    public List<OkDialy> findAll(User user) {
        List<OkDialy> list = from().where(ok.user.eq(user)).getResultList();
        return list;
    }

    public List<OkDialy> findByMonth(User user, Date month) {
        Date firstDayOfMonth = Utils.getFirstDayOfMonth(month);
        Date lastDayOfMonth = Utils.getLastDayOfMonth(month);
        return findByRange(user, firstDayOfMonth, lastDayOfMonth);
    }
    
    public List<OkDialy> findByRange(User user, Date from, Date to) {
        checkRange(from, to);
        
        List<OkDialy> list =
            from().where(
                ok.user.eq(user),
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
