package shm.controller.member;


import org.slim3.tester.JDOControllerTestCase;

import shm.controller.member.DeleteController;
import shm.model.Member;



public class DeleteControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        Member user = new Member();
        user.setEmail("aaa@aaa.com");
        user.setMemberId("aaa");
        makePersistentInTx(user);
        param("key", user.getKey());
        start("/member/delete");
        DeleteController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertEquals(0, count(Member.class));
    }
}
