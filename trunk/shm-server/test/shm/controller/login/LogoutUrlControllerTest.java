package shm.controller.login;

import org.slim3.tester.JDOControllerTestCase;

public class LogoutUrlControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/login/logoutUrl");
        LogoutUrlController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/login/logoutUrl.jsp", getNextPath());
        
        assertNotNull(requestScope("logoutUrl"));
    }
}
