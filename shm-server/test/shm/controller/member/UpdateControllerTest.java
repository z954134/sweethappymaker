package shm.controller.member;



import org.slim3.tester.JDOControllerTestCase;

import shm.controller.member.UpdateController;
import shm.model.Member;
import static shm.controller.member.MemberController.*;

public class UpdateControllerTest extends JDOControllerTestCase {

    public void testRun() throws Exception {
        Member m = new Member();
        m.setMemberId("aaa");
        makePersistentInTx(m);
        
        param("key", m.getKey());
        param("memberId", "aaa");
        sessionScope(MEMBER_ID_KEY, "bbb");
        start("/member/update");
        
        UpdateController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/member/success.jsp",getDestinationPath());
        
//        Member actual = pm.getObjectById(Member.class, m.getKey());
        
    }
}
