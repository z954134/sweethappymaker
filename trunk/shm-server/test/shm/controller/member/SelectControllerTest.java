package shm.controller.member;

import org.slim3.util.BeanMap;

import com.google.appengine.api.users.User;

import shm.common.user.MockUserService;
import shm.model.Member;
import shm.test.MyJDOControllerTestCase;

public class SelectControllerTest extends MyJDOControllerTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();

        
    }
    
    public void testRun() throws Exception {
        deleteAllInTx(Member.class);
        assertEquals(0, count(Member.class));
        Member member = new Member();
        member.setMemberId("aaa");
        member.setUser(new User("aaa@gmail.com", "gmail.com"));
        makePersistentInTx(member);
        assertEquals(1, count(Member.class));
        
        MockUserService mus = new MockUserService("aaa@gmail.com", "gmail.com", false, true);
        mus.register();
        
        start("/member/select");
        SelectController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/member/select.jsp", getDestinationPath());
        
        BeanMap actual = requestScope("member");
        assertEquals("aaa", actual.get("memberId"));
    }
}