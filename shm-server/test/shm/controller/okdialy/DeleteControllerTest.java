package shm.controller.okdialy;

import shm.test.MyJDOControllerTestCase;

public class DeleteControllerTest extends MyJDOControllerTestCase {

    public void testRun() throws Exception {
        start("/okdialy/delete");
        DeleteController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/okdialy/delete.jsp", getNextPath());
    }
}
