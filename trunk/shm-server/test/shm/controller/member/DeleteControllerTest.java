package shm.controller.member;


import static shm.controller.member.MemberController.ADMIN_ACCOUNT;
import static shm.controller.member.MemberController.MEMBER_ID_KEY;
import shm.model.Member;
import shm.test.MyJDOControllerTestCase;


public class DeleteControllerTest extends MyJDOControllerTestCase {

    Member member;
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(Member.class);
        member = new Member();
        member.setEmail("aaa@aaa.com");
        member.setMemberId("aaa");
        makePersistentInTx(member);
    }
    
    public void testRun() throws Exception {

        sessionScope(MEMBER_ID_KEY, ADMIN_ACCOUNT); 
        param("memberId", member.getMemberId());
        param("key", member.getKey());
        
        start("/member/delete");
        DeleteController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertEquals(0, count(Member.class));
    }
    
    public void testInvalidAccess() throws Exception {
        sessionScope(MEMBER_ID_KEY, "aaa"); // invalid
        param("memberId", member.getMemberId());
        param("key", member.getKey());
        
        start("/member/delete");
        assert404();
        
        
    }
}
