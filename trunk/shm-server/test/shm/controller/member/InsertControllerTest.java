package shm.controller.member;


import shm.model.Member;
import shm.test.MyJDOControllerTestCase;


public class InsertControllerTest extends MyJDOControllerTestCase {
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(Member.class);

    }
    
    public void testRun() throws Exception {
        assertEquals(0, count(Member.class));
        param("name", "川崎健");
        param("email", "sambatriste");
        start("/member/insert");
        InsertController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertEquals(1, count(Member.class));
    }
}
