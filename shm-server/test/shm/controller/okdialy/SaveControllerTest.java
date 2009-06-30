package shm.controller.okdialy;

import java.util.List;

import shm.common.Const;
import shm.common.MyController;
import shm.common.Utils;
import shm.model.Member;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

public class SaveControllerTest extends MyJDOControllerTestCase {

    private Member m = new Member();
    private String okDialyKey;
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(new Class[] { Member.class, OkDialy.class });
        tx.begin();
        m.setMemberId("aaa");
        m.setEmail("aaa@aaa.com");
        OkDialy d = new OkDialy();
        d.setDialyDate(Utils.toDate("2009/01/01"));
        d.addItem("あああ");
        d.addItem("いいい");
        m.addOkDialy(d);
        pm.makePersistent(m);
        tx.commit();
        okDialyKey = d.getKey();

    }
    
    public void testInsert() throws Exception {
        
        param("okDialyDate", "2009/01/02");
        param("item_1", "aaa");
        param("item_2", "bbb");
        sessionScope(Const.LOGIN_MEMBER_ID, m.getMemberId());
        
        start("/okdialy/save");
        
        SaveController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertEquals(2, count(OkDialy.class));
        tx.begin();
        OkDialy stored = pm.getObjectById(OkDialy.class, okDialyKey);
        assertNotNull(stored);
        Member m = stored.getMember();
        assertEquals("aaa", m.getMemberId());
    }
    
    public void testUpdate() throws Exception {

        param("okDialyDate", "2009/01/01");
        param("item_1", "aaa");
        param("item_2", "bbb");
        param("item_3", "ccc");
        sessionScope(Const.LOGIN_MEMBER_ID, m.getMemberId());

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
        
        Member m = actual.getMember();
        assertEquals("aaa", m.getMemberId());
        
    }
}
