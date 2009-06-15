package shm.controller.member;


import org.slim3.tester.JDOControllerTestCase;

import shm.controller.member.InsertController;
import shm.model.Member;


public class InsertControllerTest extends JDOControllerTestCase {
    
    public void testRun() throws Exception {
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
