package shm.controller.member;

import shm.model.Member;
import shm.test.MyJDOControllerTestCase;

public class ListControllerTest extends MyJDOControllerTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(Member.class);
        
        tx.begin();
        Member newUser = new Member();
        newUser.setMemberId("aaa");
        newUser.setEmail("aaa@aaa.com");
        pm.makePersistent(newUser);
        
        tx.commit();
    }
    
    public void testRun() throws Exception {
        
        start("/member/list");
        ListController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/member/list.jsp", getNextPath());
    }
    
}
