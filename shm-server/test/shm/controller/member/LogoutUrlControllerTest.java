package shm.controller.member;

import org.slim3.tester.JDOControllerTestCase;

public class LogoutUrlControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/member/logoutUrl");
        LogoutUrlController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/member/logoutUrl.jsp", getDestinationPath());
    }
}
