package shm.controller.login;

import org.slim3.tester.JDOControllerTestCase;

public class LoginControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/login/login");
        LoginController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/login/login.jsp", getDestinationPath());
    }
}
