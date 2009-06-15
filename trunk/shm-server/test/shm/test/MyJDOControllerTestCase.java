package shm.test;
import java.util.List;

import org.slim3.tester.JDOControllerTestCase;


public abstract class MyJDOControllerTestCase extends JDOControllerTestCase {
    
    /**
     * モデルを削除する。
     * @param modelClasses
     */
    protected final void deleteAllInTx(Class<?>...  modelClasses) {
        if (!tx.isActive()) {
            tx.begin();
        }
        for (Class<?> modelClass : modelClasses) {
            List<?> all = from(modelClass).getResultList();
            if (!all.isEmpty()) { 
                pm.deletePersistentAll();
            }
        }
        tx.commit();
        refreshPersistenceManager();
    }
    
    /** トランザクションを開始する */
    protected final void beginTx() {
        if (!tx.isActive()) {
            tx.begin();
        }
    }
}
