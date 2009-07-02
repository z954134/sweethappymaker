package shm.controller.member;

import static shm.controller.member.MemberController.ADMIN_ACCOUNT;
import static shm.controller.member.MemberController.GUEST_ACCOUNT;
import static shm.controller.member.MemberController.MEMBER_ID_KEY;
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
        sessionScope(MEMBER_ID_KEY, ADMIN_ACCOUNT);
        start("/member/list");
        ListController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/member/list.jsp", getNextPath());
    }
    
    public void testInvalidUser() throws Exception {
        sessionScope(MEMBER_ID_KEY, GUEST_ACCOUNT);
        
        start("/member/list");
        assert404();
        
        
    }
    
}
