package shm.controller.member;

import shm.model.Member;
import shm.test.MyJDOControllerTestCase;

public class LoginControllerTest extends MyJDOControllerTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(Member.class);
        
        tx.begin();
        Member m = new Member();
        m.setMemberId("aaa");
        m.setPassword("secret");
        m.setEmail("aaa@aaa.com");
        pm.makePersistent(m);
        tx.commit();
        sessionScope(MemberController.MEMBER_ID_KEY, null);
    }
    
    public void testRun() throws Exception {
        param("memberId", "aaa");
        param("password", "secret");
        
        start("/member/login");
        LoginController controller = getController();
        assertNotNull(controller);
        assertEquals(getNextPath(), "/member/success.jsp");
        
        assertNotNull(sessionScope(MemberController.MEMBER_ID_KEY));
    }
    
    public void testWhenMemberIdIncollect() throws Exception {

        param("memberId", "notexists");
        param("password", "secret");
        
        start("/member/login");
        LoginController controller = getController();
        assertNotNull(controller);
        assertEquals("/member/failure.jsp", getNextPath());
        
        assertNull(sessionScope(MemberController.MEMBER_ID_KEY));
    }
    
    public void testWhenPasswordIncollect() throws Exception {
        
        param("memberId", "aaa");
        param("password", "incollectpassword");
        
        start("/member/login");
        LoginController controller = getController();
        assertNotNull(controller);
        assertEquals("/member/failure.jsp", getNextPath());
        
        assertNull(sessionScope(MemberController.MEMBER_ID_KEY));
    }
    
    
}
