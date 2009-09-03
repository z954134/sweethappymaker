package shm.dao;

import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import org.slim3.jdo.SelectQuery;

import shm.meta.ColMeta;
import shm.model.Col;
import shm.model.Member;

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

    public Col find(Member member) {
        return from().where(m.member.eq(member)).getSingleResult();
    }

}
