package shm.dao;

import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import org.slim3.jdo.SelectQuery;

import shm.model.Col;
import shm.model.ColMeta;

import com.google.appengine.api.users.User;

public class ColDao extends MyGenericDao<Col> {

    private static final ColMeta m = new ColMeta();

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(ColDao.class.getName());

    public ColDao() {
        super(Col.class);
    }

    public ColDao(PersistenceManager pm) {
        super(Col.class, pm);
    }

    @Override
    protected SelectQuery<Col> from() {
        return new SelectQuery<Col>(pm, m.getModelClass());
    }

    public Col find(User user) {
        return from().where(m.user.eq(user)).getSingleResult();
    }

}
