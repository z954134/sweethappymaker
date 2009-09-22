package shm.controller.signup;

import shm.cool.common.Const;
import shm.cool.common.user.MockUserService;
import shm.dao.MemberDao;
import shm.model.Member;
import shm.test.MyJDOControllerTestCase;

public class RegisterIdControllerTest extends MyJDOControllerTestCase {

    public void testRun() throws Exception {
        MockUserService mock = new MockUserService("aaa", "gmail.com");
        mock.register();
        requestScope("memberId", "aaa");
        
        start("/signup/registerId");
        RegisterIdController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals(Const.APP_MAIN_URL, getDestinationPath());
    }
    
    public void testDuplicated() throws Exception {
        deleteAllInTx(Member.class);
        Member m = new Member();
        m.setMemberId("aaa");
        m.setPassword("12345678");
        makePersistentInTx(m);
        
        MemberDao dao = new MemberDao();
        assertTrue(dao.exists("aaa"));
        
        MockUserService mock = new MockUserService("aaa", "gmail.com");
        mock.register();
        requestScope("memberId", "aaa");
        
        start("/signup/registerId");
        RegisterIdController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/signup/gaccount.jsp", getDestinationPath());
    }
}
