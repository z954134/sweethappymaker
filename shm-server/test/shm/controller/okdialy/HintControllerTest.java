package shm.controller.okdialy;

import shm.test.MyJDOControllerTestCase;

public class HintControllerTest extends MyJDOControllerTestCase {

    
    public void testRun() throws Exception {
        requestScope("previousHintKey", "hint.001");
        
        start("/okdialy/hint");
        HintController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/okdialy/hint.jsp", getDestinationPath());
        assertNotNull(requestScope("hint"));
        assertNotNull(requestScope("hintKey"));
        assertFalse("hint.001".equals(requestScope("hintKey")));
    }
}
