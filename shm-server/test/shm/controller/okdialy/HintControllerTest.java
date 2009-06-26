package shm.controller.okdialy;

import org.slim3.tester.JDOControllerTestCase;

public class HintControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        start("/okdialy/hint");
        HintController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/okdialy/hint.jsp", getNextPath());
    }
}
