package shm.controller.member;

import shm.common.Const;
import shm.test.MyJDOControllerTestCase;

public class LogoutControllerTest extends MyJDOControllerTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        sessionScope(Const.LOGIN_MEMBER_ID, "aaa");
    }
    
    public void testRun() throws Exception {
        assertEquals("aaa", sessionScope(Const.LOGIN_MEMBER_ID));
        start("/member/logout");
        LogoutController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertNull(sessionScope(Const.LOGIN_MEMBER_ID));
    }
}
