package shm.controller.signup;

import org.slim3.tester.JDOControllerTestCase;

public class SignupControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/signup/signup");
        SignupController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/signup/signup.jsp", getDestinationPath());
    }
}
