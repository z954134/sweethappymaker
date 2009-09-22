package shm.test;
import java.util.Date;
import java.util.List;

import javax.jdo.Transaction;

import org.slim3.tester.JDOControllerTestCase;


import shm.controller.member.MemberController;
import shm.cool.common.Utils;
import shm.cool.common.user.UserServiceUtil;


public abstract class MyJDOControllerTestCase extends JDOControllerTestCase {
    
    protected final Transaction tx() {
        return pm.currentTransaction();
    }
    
    @Override
    protected void tearDown() throws Exception {
        UserServiceUtil.resetUserServiceFactory();
        super.tearDown();
    }

    protected final void login(String memberId) {
        sessionScope(MemberController.MEMBER_ID_KEY, memberId);
    }
    /**
     * モデルを削除する。
     * @param modelClasses
     */
    protected final void deleteAllInTx(Class<?>...  modelClasses) {
        tx().begin();
        deleteAll(modelClasses);
        tx().commit();
    }
    
    protected final void deleteAll(Class<?>... modelClasses) {
        for (Class<?> modelClass : modelClasses) {
            List<?> all = from(modelClass).getResultList();
            if (all == null || all.isEmpty()) continue;
            pm.deletePersistentAll(all);
        }        
    }
    
    /** トランザクションを開始する */
    protected final void beginTx() {
        if (!tx().isActive()) {
            tx().begin();
        }
    }

    protected static void assertEquals(String expected, Date actual) {
        assertEquals("", expected, actual);
    }
    protected static void assertEquals(String msg, String expected, Date actual) {
        assertEquals(msg, expected, Utils.toString(actual));
    }
}
