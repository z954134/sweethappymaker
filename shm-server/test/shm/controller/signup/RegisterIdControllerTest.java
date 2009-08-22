package shm.controller.signup;

import shm.common.user.MockUserService;
import shm.test.MyJDOControllerTestCase;

public class RegisterIdControllerTest extends MyJDOControllerTestCase {

    public void testRun() throws Exception {
        MockUserService mock = new MockUserService("aaa", "gmail.com");
        mock.register();
        requestScope("memberId", "aaa");
        
        start("/signup/registerId");
        RegisterIdController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("signup.jsp", getDestinationPath());
    }
}
