package shm.controller.login;

import org.slim3.tester.JDOControllerTestCase;

public class LoginUrlControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/login/loginUrl");
        LoginUrlController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/login/loginUrl.jsp", getDestinationPath());
        assertNotNull(requestScope("loginUrl"));
    }
}
