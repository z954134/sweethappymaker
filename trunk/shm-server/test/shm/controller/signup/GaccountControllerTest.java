package shm.controller.signup;

import shm.test.MyJDOControllerTestCase;

public class GaccountControllerTest extends MyJDOControllerTestCase {

    public void testRun() throws Exception {
        start("/signup/gaccount");
        GaccountController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/signup/gaccount.jsp", getDestinationPath());
    }
}
