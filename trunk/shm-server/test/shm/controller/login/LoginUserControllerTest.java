package shm.controller.login;

import shm.test.MyJDOControllerTestCase;

public class LoginUserControllerTest extends MyJDOControllerTestCase {

    public void testRun() throws Exception {
        start("/login/loginUser");
        LoginUserController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/login/loginUser.jsp", getDestinationPath());
        assertEquals("GUEST", asString("loginUser"));
    }
}
