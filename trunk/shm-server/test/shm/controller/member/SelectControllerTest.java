package shm.controller.member;

import org.slim3.util.BeanMap;

import shm.model.Member;
import shm.test.MyJDOControllerTestCase;

public class SelectControllerTest extends MyJDOControllerTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(Member.class);
        
        tx.begin();
        Member newUser = new Member();
        newUser.setMemberId("guest");
        newUser.setPassword("secret");
        newUser.setEmail("aaa@aaa.com");
        pm.makePersistent(newUser);
        
        tx.commit();
    }
    
    public void testRun() throws Exception {
        
        start("/member/select");
        SelectController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/member/select.jsp", getNextPath());
        
        BeanMap actual = requestScope("member");
        assertEquals("guest", actual.get("memberId"));
        assertEquals("secret", actual.get("password"));
        assertEquals("aaa@aaa.com",actual.get("email"));
    }
}
