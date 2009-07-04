package shm.controller.login;

import org.slim3.tester.JDOControllerTestCase;

import shm.controller.login.LoginUserController;

public class LoginUserControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/login/loginUser");
        LoginUserController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/login/loginUser.jsp", getNextPath());
        assertEquals("GUEST", asString("loginUser"));
    }
}
