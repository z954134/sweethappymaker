package shm.controller.col;

import shm.test.MyJDOControllerTestCase;

public class SaveControllerTest extends MyJDOControllerTestCase {

    
    public void testRun() throws Exception {
        login("aaa");
        start("/col/save");
        SaveController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getDestinationPath());
    }
}
