package shm.controller.signup;

import org.slim3.tester.JDOControllerTestCase;

import shm.cool.common.Const;

public class SignupControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        requestScope("memberId", "aaa");
        requestScope("password", "12345678");
        
        start("/signup/signup");
        SignupController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals(Const.APP_MAIN_URL, getDestinationPath());
    }
}
