package shm.controller.login;

import org.slim3.tester.JDOControllerTestCase;

public class GLoginControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/login/gLogin");
        GLoginController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("/login/", getDestinationPath());
    }
}
