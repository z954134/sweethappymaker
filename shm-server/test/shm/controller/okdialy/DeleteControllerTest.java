package shm.controller.okdialy;

import shm.common.Utils;
import shm.controller.member.MemberController;
import shm.model.Member;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

public class DeleteControllerTest extends MyJDOControllerTestCase {

    private Member m = new Member();
    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(new Class[] { Member.class, OkDialy.class });
        beginTx();
        m.setMemberId("aaa");
//        m.setEmail("aaa@aaa.com");
        OkDialy d = new OkDialy();
        d.setDialyDate(Utils.toDate("2009/01/01"));
        d.addItem("あああ");
        d.addItem("いいい");
        m.addOkDialy(d);
        pm.makePersistent(m);
        tx.commit();

    }
    
    public void testRun() throws Exception {
        param("okDialyDate", "2009/01/01");
        sessionScope(MemberController.MEMBER_ID_KEY, m.getMemberId());
        
        start("/okdialy/delete");
        DeleteController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getNextPath());
        assertEquals(0, count(OkDialy.class));
    }
}
