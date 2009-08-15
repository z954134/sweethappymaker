package shm.controller.member;


import java.util.List;

import shm.model.Member;
import shm.test.MyJDOControllerTestCase;


public class InsertControllerTest extends MyJDOControllerTestCase {
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(Member.class);

    }
    
    public void testRun() throws Exception {
        assertEquals(0, count(Member.class));

        start("/member/insert");
        InsertController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/member/success.jsp", getDestinationPath());
        assertEquals(1, count(Member.class));
    }
    
    public void testRunDuplicated() throws Exception {
        Member m = new Member();
        m.setMemberId("aaa");
        makePersistentInTx(m);
        assertEquals(1, count(Member.class));
        
        param("name", "aaa");
        param("email", "pass");
        start("/member/insert");
        assertFalse(isRedirect());
        assertEquals("/member/failure.jsp", getDestinationPath());
        assertEquals(1, count(Member.class));
        List<String> messageList = requestScope("messageList");
        assertEquals(1, messageList.size());
    }
}
