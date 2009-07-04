package shm.dao;

import java.util.Date;
import java.util.List;

import shm.model.OkDialy;
import shm.model.OkDialyMeta;

import com.google.appengine.api.users.User;

public class OkDialyDao extends MyGenericDao<OkDialy> {

    private OkDialyMeta ok = new OkDialyMeta();
    
    public OkDialyDao() {
        super(OkDialy.class);
    }

    public OkDialy select(User user, Date dialyDate) {
        OkDialy okDialy =
            from()
                .where(ok.user.eq(user), ok.dialyDate.eq(dialyDate))
                .getSingleResult();
        return okDialy;
    }
    
    public List<OkDialy> selectAll(User user) {
        List<OkDialy> list = from().where(ok.user.eq(user)).getResultList();
        return list;
    }
}
