package shm.controller.member;

import shm.test.MyJDOControllerTestCase;

public class LoginUrlControllerTest extends MyJDOControllerTestCase {

    public void testRun() throws Exception {
        
        start("/member/loginUrl");
        LoginUrlController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/member/loginUrl.jsp", getNextPath());
        
        assertEquals("/_ah/login?continue=%2F", requestScope("loginUrl"));
    }
}

