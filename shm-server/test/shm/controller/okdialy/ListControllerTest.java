package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.List;

import org.slim3.util.DateUtil;

import shm.cool.common.user.MockUserService;
import shm.model.Col;
import shm.model.Member;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

import com.google.appengine.api.users.User;

public class ListControllerTest extends MyJDOControllerTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();

        deleteAllInTx(new Class[] { Member.class, OkDialy.class, Col.class });
        
        OkDialy okDialy = new OkDialy();
        okDialy.setDialyDate(DateUtil.toDate("2009-01-22"));
        List<String> items = new ArrayList<String>();
        items.add("いいこと１");
        items.add("いいこと２");
        okDialy.setItems(items);
        User user = new User("aaa@gmail.com", "gmail.com");
        Member member = new Member("aaa", user);
        
        okDialy.setMember(member);
        makePersistentInTx(member);

        MockUserService mus = new MockUserService(user);
        mus.register();
        
        login("aaa");
    }
    
    public void testRun() throws Exception {
        
        start("/okdialy/list");
        ListController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/okdialy/list.jsp", getDestinationPath());
        
        List<OkDialy> actual = requestScope("okDialyList");
        assertNotNull(actual);
        assertEquals(1, actual.size());
    }
}
