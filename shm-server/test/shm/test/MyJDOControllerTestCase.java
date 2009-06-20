package shm.test;
import java.util.Date;
import java.util.List;

import org.slim3.tester.JDOControllerTestCase;
import org.slim3.util.DateUtil;


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

    protected static void assertEquals(String expected, Date actual) {
        assertEquals("", expected, actual);
    }
    protected static void assertEquals(String msg, String expected, Date actual) {
        assertEquals(msg, expected, DateUtil.toString(actual));
    }
}
