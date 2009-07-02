package shm.controller.member;

import shm.test.MyJDOControllerTestCase;

public class LogoutControllerTest extends MyJDOControllerTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        sessionScope(MemberController.MEMBER_ID_KEY, "aaa");
    }
    
    public void testRun() throws Exception {
        assertEquals("aaa", sessionScope(MemberController.MEMBER_ID_KEY));
        start("/member/logout");
        LogoutController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertNull(sessionScope(MemberController.MEMBER_ID_KEY));
    }
}
