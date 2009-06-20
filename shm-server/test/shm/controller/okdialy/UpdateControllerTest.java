package shm.controller.okdialy;

import org.slim3.tester.ControllerTestCase;

public class UpdateControllerTest extends ControllerTestCase {

    public void testRun() throws Exception {
        start("/okdialy/update");
        UpdateController controller = getController();
        assertNotNull(controller);
//        assertTrue(isRedirect());
//        assertEquals("/okdialy/", getNextPath());
    }
}
