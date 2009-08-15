package shm.controller.signup;

import org.slim3.tester.JDOControllerTestCase;

public class RegisterIdControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/signup/registerId");
        RegisterIdController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/signup/registerId.jsp", getDestinationPath());
    }
}
