package shm.controller.member;

import shm.common.MyJDOController;
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
        sessionScope(LoginController.LOGIN_MEMBER_KEY, null);
    }
    
    public void testRun() throws Exception {
        param("memberId", "aaa");
        param("password", "secret");
        
        start("/member/login");
        MyJDOController controller = getController();
        assertNotNull(controller);
        assertEquals(getNextPath(), "/member/login_success.jsp");
        
        assertNotNull(sessionScope(LoginController.LOGIN_MEMBER_KEY));
    }
    
    public void testWhenMemberIdIncollect() throws Exception {

        param("memberId", "notexists");
        param("password", "secret");
        
        start("/member/login");
        MyJDOController controller = getController();
        assertNotNull(controller);
        assertEquals("/member/login_failure.jsp", getNextPath());
        
        assertNull(sessionScope(LoginController.LOGIN_MEMBER_KEY));
    }
    
    public void testWhenPasswordIncollect() throws Exception {
        
        param("memberId", "aaa");
        param("password", "incollectpassword");
        
        start("/member/login");
        MyJDOController controller = getController();
        assertNotNull(controller);
        assertEquals("/member/login_failure.jsp", getNextPath());
        
        assertNull(sessionScope(LoginController.LOGIN_MEMBER_KEY));
    }
    
    
}
