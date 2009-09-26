package shm.dao;

import javax.jdo.PersistenceManager;

import org.slim3.jdo.GenericDao;

/**
 * 汎用DAO
 * @author Tsuyoshi
 *
 * @param <T> 対象となるエンティティ
 */
public abstract class MyGenericDao<T> extends GenericDao<T> {

    
    public MyGenericDao(Class<T> modelClass) {
        super(modelClass);
    }

    /**
     * コンストラクタ
     * @param pm PersistentManager
     * @param clazz 対象モデルクラス
     */
    public MyGenericDao(Class<T> modelClass, PersistenceManager pm) {
        super(modelClass, pm);
    }

    public void deleteObjectById(Object key) {
        T model = getObjectById(key.toString());
        deletePersistent(model);
    }
    
}
