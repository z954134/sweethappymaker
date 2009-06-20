package shm.controller.okdialy;

import shm.common.Const;
import shm.model.Member;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

public class InsertControllerTest extends MyJDOControllerTestCase {

    private Member m = new Member();
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
     
        deleteAllInTx(new Class[] { Member.class, OkDialy.class });
        tx.begin();
        m.setMemberId("aaa");
        m.setEmail("aaa@aaa.com");
        pm.makePersistent(m);
        tx.commit();
        refreshPersistenceManager();
    }
    
    public void testRun() throws Exception {
        
        param("dialyDate", "2009-01-01");
        param("item1", "aaa");
        param("item2", "bbb");
        sessionScope(Const.LOGIN_MEMBER_ID, m.getMemberId());
        
        start("/okdialy/insert");
        
        InsertController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertEquals(1, count(OkDialy.class));
        
        OkDialy stored = from(OkDialy.class).getFirstResult();
        Member m = stored.getMember();
        assertEquals("aaa", m.getMemberId());
    }
}
