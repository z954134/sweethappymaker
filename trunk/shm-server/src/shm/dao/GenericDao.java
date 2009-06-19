package shm.dao;

import javax.jdo.PersistenceManager;

import shm.common.Select;

/**
 * 汎用DAO
 * @author Tsuyoshi
 *
 * @param <T> 対象となるエンティティ
 */
public abstract class GenericDao<T> {

    protected final Class<T> entityClass;
    protected final PersistenceManager pm;
   
    /**
     * コンストラクタ
     * @param pm PersistentManager
     * @param clazz 対象絵印ティティクラス
     */
    public GenericDao(PersistenceManager pm, Class<T> entityClass) {
        this.pm = pm;
        this.entityClass = entityClass;
    }
    
    /**
     * IDによりオブジェクトを取得する。
     * @param id ID
     * @return オブジェクト
     * @see PersistenceManager#getObjectById(Class, Object)
     */
    public T getObjectById(Object id) {
        return pm.getObjectById(entityClass, id);
    }
    
    /**
     * SELECTを発行する。
     * @return SELECT
     */
    protected Select select() {
        return new Select(pm);
    }
    
    /**
     * IDによりオブジェクトを削除する。
     * @param id ID
     */
    public void deleteObjectById(Object id) {
        Object o = getObjectById(id);
        pm.deletePersistent(o);
    }
    
}
