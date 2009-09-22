package shm.controller.login;

import shm.cool.common.user.MockUserService;
import shm.test.MyJDOControllerTestCase;

public class GoogleLoginControllerTest extends MyJDOControllerTestCase {

    public void testRun() throws Exception {
        MockUserService mock = new MockUserService();
        mock.register();
        
        start("/login/GoogleLogin");
        GoogleLoginController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("/login/GoogleLogin", getDestinationPath());
        
    }
    
    public void testForwardToRegiserId() throws Exception {
        MockUserService mock = new MockUserService("aaa", "gmail.com");
        mock.register();
        
        start("/login/GoogleLogin");
        GoogleLoginController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/signup/gaccount", getDestinationPath());
        
    }
    
}
