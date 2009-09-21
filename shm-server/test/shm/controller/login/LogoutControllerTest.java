package shm.controller.login;

import shm.test.MyJDOControllerTestCase;

public class LogoutControllerTest extends MyJDOControllerTestCase {

    public void testRun() throws Exception {
        assertNotNull(request.getSession(true));

        start("/login/Logout");
        LogoutController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertTrue(getDestinationPath().endsWith("login"));
        
        assertNotNull(request.getSession(false));
    }
}
