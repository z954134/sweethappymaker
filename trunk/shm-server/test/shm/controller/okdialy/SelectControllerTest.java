package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.List;

import org.slim3.util.DateUtil;

import shm.common.user.MockUserService;
import shm.model.Member;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

import com.google.appengine.api.users.User;

public class SelectControllerTest extends MyJDOControllerTestCase {
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(OkDialy.class);
        
        OkDialy okDialy = new OkDialy();
        okDialy.setDialyDate(DateUtil.toDate("2009-01-22"));
        List<String> items = new ArrayList<String>();
        items.add("いいこと１");
        items.add("いいこと２");
        okDialy.setItems(items);
        User user = new User("aaa@gmail.com", "gmail.com");
        Member member = new Member("aaa", user);
        login("aaa");
        member.setUser(user);
        okDialy.setMember(member);
        
        makePersistentInTx(okDialy);

        MockUserService mus = new MockUserService(user);
        mus.register();
        
    }
    public void testRun() throws Exception {
        
        param("dialyDate", "2009/01/22");
        start("/okdialy/select");
        SelectController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/okdialy/select.jsp", getDestinationPath());
        OkDialy okDialy = requestScope("okDialy");
        assertNotNull(okDialy);
        assertEquals("2009/01/22", okDialy.getDialyDate());
        List<String> items = okDialy.getItems();
        assertEquals(2, items.size());
    }
}
