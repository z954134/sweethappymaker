package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.List;

import org.slim3.util.BeanMap;

import shm.common.Utils;
import shm.common.user.MockUserService;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

import com.google.appengine.api.users.User;

public class MonthlyListControllerTest extends MyJDOControllerTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();

        deleteAllInTx(new Class[] { OkDialy.class });

        User user = new User("aaa@gmail.com", "gmail.com");
        List<String> items = new ArrayList<String>();
        items.add("いいこと１");
        items.add("いいこと２");
        
        {
            OkDialy okDialy = new OkDialy();
            okDialy.setDialyDate(Utils.toDate("2008/12/31"));
            okDialy.setItems(items);
            okDialy.setUser(user);
            makePersistentInTx(okDialy);
        }
        
        {
            OkDialy okDialy = new OkDialy();
            okDialy.setDialyDate(Utils.toDate("2009/01/01"));
            okDialy.setItems(items);
            okDialy.setUser(user);
            makePersistentInTx(okDialy);
        }
        {
            OkDialy okDialy = new OkDialy();
            okDialy.setDialyDate(Utils.toDate("2009/01/31"));
            okDialy.setItems(items);
            okDialy.setUser(user);
            makePersistentInTx(okDialy);
        }
        {
            OkDialy okDialy = new OkDialy();
            okDialy.setDialyDate(Utils.toDate("2009/02/01"));
            okDialy.setItems(items);
            okDialy.setUser(user);
            makePersistentInTx(okDialy);
        }
        
        
        MockUserService mus = new MockUserService("aaa@gmail.com", "gmail.com");
        mus.register();

    }

    public void testRun() throws Exception {
        requestScope("year", "2009");
        requestScope("month", "1");
        start("/okdialy/monthlyList");
        MonthlyListController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/okdialy/monthlyList.jsp", getNextPath());
        
        List<BeanMap> actual = requestScope("dialyList");
        assertEquals(2, actual.size());
        assertEquals("2009/01/01", actual.get(0).get("dialyDateText"));
        assertEquals("2009/01/31", actual.get(1).get("dialyDateText"));
        
    }
}
