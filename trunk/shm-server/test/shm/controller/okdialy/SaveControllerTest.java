package shm.controller.okdialy;

import java.util.List;

import shm.common.MyController;
import shm.common.Utils;
import shm.common.user.MockUserService;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

import com.google.appengine.api.users.User;

public class SaveControllerTest extends MyJDOControllerTestCase {

    private String okDialyKey;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(OkDialy.class);

        OkDialy d = new OkDialy();
        d.setDialyDate(Utils.toDate("2009/01/01"));
        d.addItem("あああ");
        d.addItem("いいい");
        User user = new User("aaa@gmail.com", "gmail.com");
        d.setUser(user);
        makePersistentInTx(d);

        okDialyKey = d.getKey();
        MockUserService mus = new MockUserService(user);
        mus.register();
    }

    public void testInsert() throws Exception {

        param("okDialyDate", "2009/01/02");
        request.setParameter("item", new String[] { "aaa", "bbb" });
        start("/okdialy/save");

        SaveController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertEquals(2, count(OkDialy.class));
        tx.begin();
        OkDialy stored = pm.getObjectById(OkDialy.class, okDialyKey);
        assertNotNull(stored);
        User user = stored.getUser();
        assertEquals("aaa@gmail.com", user.getEmail());
    }

    public void testUpdate() throws Exception {

        param("okDialyDate", "2009/01/01");
        request.setParameter("item", new String[] { "aaa", "bbb", "ccc" });

        start("/okdialy/save");
        MyController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertEquals(1, count(OkDialy.class));
        tx.begin();
        OkDialy actual = pm.getObjectById(OkDialy.class, okDialyKey);
        assertNotNull(actual);
        List<String> actualItems = actual.getItems();

        assertEquals("aaa", actualItems.get(0));
        assertEquals("bbb", actualItems.get(1));
        assertEquals("ccc", actualItems.get(2));

        User m = actual.getUser();
        assertEquals("aaa@gmail.com", m.getEmail());
    }
}
