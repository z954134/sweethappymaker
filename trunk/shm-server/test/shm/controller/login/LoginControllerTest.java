package shm.controller.login;

import org.slim3.controller.validator.Errors;
import org.slim3.tester.JDOControllerTestCase;

public class LoginControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        requestScope("memberId", "aaa");
        requestScope("password", "12345678");
        
        start("/login/login");
        LoginController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("/", getDestinationPath());
    }
    
    
    public void testMemberIdRequired() throws Exception {
        requestScope("password", "12345678");

        start("/login/login");
        LoginController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/login/index", getDestinationPath());
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
        assertEquals("/login/index", getDestinationPath());
        Errors errors = requestScope("errors");
        assertNotNull(errors);
        assertEquals(1, errors.size());
    }
}
