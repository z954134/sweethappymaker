package shm.controller.login;

import org.slim3.tester.JDOControllerTestCase;

public class IndexControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/login/index");
        IndexController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/login/index.jsp", getDestinationPath());
    }
}
