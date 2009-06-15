package shm.controller.member;



import org.slim3.tester.JDOControllerTestCase;

import shm.controller.member.UpdateController;
import shm.model.Member;


public class UpdateControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        Member newUser = new Member();
        newUser.setMemberId("aaa");
        newUser.setEmail("aaa@aaa.com");
        makePersistentInTx(newUser);
        
        param("key", newUser.getKey());
        param("memberId", "bbb");
        param("email", "bbb@bbb.com");
        start("/member/update");
        
        UpdateController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        
        Member actual = pm.getObjectById(Member.class, newUser.getKey());
        assertEquals("bbb", actual.getMemberId());
        assertEquals("bbb@bbb.com", actual.getEmail());
    }
}
