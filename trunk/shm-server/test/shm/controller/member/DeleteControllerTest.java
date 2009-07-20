package shm.controller.member;

import shm.model.Member;
import shm.test.MyJDOControllerTestCase;

public class DeleteControllerTest extends MyJDOControllerTestCase {

    Member member;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(Member.class);
        member = new Member();
        member.setMemberId("aaa");
        makePersistentInTx(member);
    }

    public void testRun() throws Exception {

        param("memberId", member.getMemberId());
        param("key", member.getKey());

        start("/member/delete");
        DeleteController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertEquals(0, count(Member.class));
    }

}
