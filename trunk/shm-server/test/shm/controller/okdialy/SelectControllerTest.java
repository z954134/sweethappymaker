package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.List;

import org.slim3.util.BeanMap;
import org.slim3.util.BeanUtil;
import org.slim3.util.DateUtil;

import shm.common.user.MockUserService;
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
        okDialy.setUser(user);
        
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
        assertEquals("/okdialy/select.jsp", getNextPath());
        BeanMap map = requestScope("okDialy");
        assertNotNull(map);
        OkDialy okDialy = new OkDialy();
        BeanUtil.copy(map, okDialy);
        assertEquals("2009/01/22", okDialy.getDialyDate());
        List<String> items = okDialy.getItems();
        assertEquals(2, items.size());
    }
}
