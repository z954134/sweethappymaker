package shm.controller.member;


import org.slim3.tester.JDOControllerTestCase;

import shm.controller.member.DeleteController;
import shm.model.Member;



public class DeleteControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        Member member = new Member();
        member.setEmail("aaa@aaa.com");
        member.setMemberId("aaa");
        makePersistentInTx(member);
        
        param("key", member.getKey());
        start("/member/delete");
        DeleteController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertEquals(0, count(Member.class));
    }
}
