package shm.controller.member;

import shm.common.Const;
import shm.common.MyController;
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
        sessionScope(Const.LOGIN_MEMBER_ID, null);
    }
    
    public void testRun() throws Exception {
        param("memberId", "aaa");
        param("password", "secret");
        
        start("/member/login");
        MyController controller = getController();
        assertNotNull(controller);
        assertEquals(getNextPath(), "/member/login_success.jsp");
        
        assertNotNull(sessionScope(Const.LOGIN_MEMBER_ID));
    }
    
    public void testWhenMemberIdIncollect() throws Exception {

        param("memberId", "notexists");
        param("password", "secret");
        
        start("/member/login");
        MyController controller = getController();
        assertNotNull(controller);
        assertEquals("/member/login_failure.jsp", getNextPath());
        
        assertNull(sessionScope(Const.LOGIN_MEMBER_ID));
    }
    
    public void testWhenPasswordIncollect() throws Exception {
        
        param("memberId", "aaa");
        param("password", "incollectpassword");
        
        start("/member/login");
        MyController controller = getController();
        assertNotNull(controller);
        assertEquals("/member/login_failure.jsp", getNextPath());
        
        assertNull(sessionScope(Const.LOGIN_MEMBER_ID));
    }
    
    
}
