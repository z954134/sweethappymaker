package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.List;

import org.slim3.util.DateUtil;

import shm.common.user.MockUserService;
import shm.controller.member.MemberController;
import shm.model.Member;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

import com.google.appengine.api.users.User;

public class ListControllerTest extends MyJDOControllerTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Member m = new Member(); 
        deleteAllInTx(new Class[] { Member.class, OkDialy.class });
        
        m.setMemberId("aaa");
        m.setUser(new User("aaa@gmail.com", "gmail.com"));
        OkDialy okDialy = new OkDialy();
        okDialy.setDialyDate(DateUtil.toDate("2009-01-22"));
        List<String> items = new ArrayList<String>();
        items.add("いいこと１");
        items.add("いいこと２");
        okDialy.setItems(items);
        
        m.addOkDialy(okDialy);
        m.addOkDialy(okDialy);
        makePersistentInTx(m);

        MockUserService mus = new MockUserService("aaa@gmail.com", "gmail.com", false, true);
        mus.register();
        
    }
    
    public void testRun() throws Exception {
        sessionScope(MemberController.MEMBER_ID_KEY, "aaa");
        start("/okdialy/list");
        ListController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/okdialy/list.jsp", getNextPath());
    }
}
