package shm.controller.col;

import org.slim3.tester.JDOControllerTestCase;

public class SaveControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/col/save");
        SaveController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
    }
}
