package shm.dao;

import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import shm.model.OkDialyHint;
import shm.model.OkDialyHintMeta;

public class OkDialyHintDao extends MyGenericDao<OkDialyHint> {

    @SuppressWarnings("unused")
    private static final OkDialyHintMeta m = new OkDialyHintMeta();

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(OkDialyHintDao.class.getName());

    public OkDialyHintDao(PersistenceManager pm) {
        super(pm, OkDialyHint.class);
    }

}
