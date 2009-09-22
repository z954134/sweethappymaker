package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.List;

import shm.cool.common.Utils;
import shm.cool.common.user.MockUserService;
import shm.model.Member;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

import com.google.appengine.api.users.User;

public class MonthlyDialyDaysControllerTest extends MyJDOControllerTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();

        deleteAllInTx(new Class[] { OkDialy.class });

        User user = new User("aaa@gmail.com", "gmail.com");
        Member member = new Member("aaa", user);
        login("aaa");
        
        List<String> items = new ArrayList<String>();
        items.add("いいこと１");
        items.add("いいこと２");
        
        {
            OkDialy okDialy = new OkDialy();
            okDialy.setDialyDate(Utils.toDate("2008/12/31"));
            okDialy.setItems(items);
            okDialy.setMember(member);
            makePersistentInTx(okDialy);
        }
        
        {
            OkDialy okDialy = new OkDialy();
            okDialy.setDialyDate(Utils.toDate("2009/01/01"));
            okDialy.setItems(items);
            okDialy.setMember(member);
            makePersistentInTx(okDialy);
        }
        {
            OkDialy okDialy = new OkDialy();
            okDialy.setDialyDate(Utils.toDate("2009/01/31"));
            okDialy.setItems(items);
            okDialy.setMember(member);
            makePersistentInTx(okDialy);
        }
        {
            OkDialy okDialy = new OkDialy();
            okDialy.setDialyDate(Utils.toDate("2009/02/01"));
            okDialy.setItems(items);
            okDialy.setMember(member);
            makePersistentInTx(okDialy);
        }
        
        
        MockUserService mus = new MockUserService("aaa@gmail.com", "gmail.com");
        mus.register();

    }

    public void testRun() throws Exception {
        requestScope("year", "2009");
        requestScope("month", "0");
        start("/okdialy/monthlyDialyDays");
        MonthlyDialyDaysController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/okdialy/monthlyDialyDays.jsp", getDestinationPath());
        
        List<String> actual = requestScope("monthlyDialyDays");
        assertEquals(2, actual.size());
        assertEquals("2009/01/01", actual.get(0));
        assertEquals("2009/01/31", actual.get(1));
        
    }
}
