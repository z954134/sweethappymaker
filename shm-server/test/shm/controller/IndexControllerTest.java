package shm.controller;

import org.slim3.tester.ControllerTestCase;

public class IndexControllerTest extends ControllerTestCase {

    public void testRun() throws Exception {
        
        start("/");
        IndexController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals("/login", getDestinationPath());
    }
}
