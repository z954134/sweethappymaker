package shm.controller.login;

import org.slim3.controller.validator.Errors;
import org.slim3.tester.JDOControllerTestCase;

import shm.cool.common.Const;
import shm.dao.MemberDao;
import shm.model.Member;

public class LoginControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        MemberDao dao = new MemberDao();
        Member m = new Member();
        m.setMemberId("aaa");
        m.setPassword("12345678");
        dao.makePersistentInTx(m);
        
        requestScope("memberId", "aaa");
        requestScope("password", "12345678");
        
        start("/login/login");
        LoginController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals(Const.APP_MAIN_URL, getDestinationPath());
    }
    
    
    public void testMemberIdRequired() throws Exception {
        requestScope("password", "12345678");

        start("/login/login");
        LoginController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/login/index.jsp", getDestinationPath());
        Errors errors = requestScope("errors");
        assertNotNull(errors);
        assertEquals(1, errors.size());
    }
    
    public void testMemberIdPasswordMinLength() throws Exception {
        requestScope("memberId", "aaa");
        requestScope("password", "1234567");

        start("/login/login");
        LoginController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/login/index.jsp", getDestinationPath());
        Errors errors = requestScope("errors");
        assertNotNull(errors);
        assertEquals(2, errors.size());
    }
}
