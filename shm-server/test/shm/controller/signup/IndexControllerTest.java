package shm.controller.signup;

import org.slim3.tester.JDOControllerTestCase;

import shm.controller.login.IndexController;

public class IndexControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/signup/index");
        IndexController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/signup/index.jsp", getDestinationPath());
    }
}
