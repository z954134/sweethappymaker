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

    /**
     * コンストラクタ
     * @param pm PersistentManager
     * @param clazz 対象モデルクラス
     */
    public MyGenericDao(PersistenceManager pm, Class<T> modelClass) {
        super(pm, modelClass);
    }

    public void deleteObjectById(Object key) {
        T model = find(key);
        delete(model);
    }
    
    public T find(Object key) {
        return find(key.toString());
    }
}
