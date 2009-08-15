package shm.controller.col;

import org.slim3.tester.JDOControllerTestCase;

public class SelectControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/col/select");
        SelectController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/col/select.jsp", getDestinationPath());
    }
}
