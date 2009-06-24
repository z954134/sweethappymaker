package shm.controller.okdialy;

import shm.common.Const;
import shm.common.Utils;
import shm.model.Member;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

public class UpdateControllerTest extends MyJDOControllerTestCase {

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
        refreshPersistenceManager();
    }

    public void testRun() throws Exception {

        param("okDialyDate", "2009/01/01");
        param("item_1", "aaa");
        param("item_2", "bbb");
        sessionScope(Const.LOGIN_MEMBER_ID, m.getMemberId());

        start("/okdialy/update");
        UpdateController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertEquals(1, count(OkDialy.class));
        tx.begin();
        OkDialy actual = pm.getObjectById(OkDialy.class, okDialyKey);
        assertNotNull(actual);
        assertEquals("aaa", actual.getItems().get(0));
        assertEquals("bbb", actual.getItems().get(1));
        
    }
}
