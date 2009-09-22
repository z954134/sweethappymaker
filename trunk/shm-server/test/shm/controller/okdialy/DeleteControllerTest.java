package shm.controller.okdialy;

import shm.cool.common.Utils;
import shm.cool.common.user.MockUserService;
import shm.model.Member;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

public class DeleteControllerTest extends MyJDOControllerTestCase {

    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(OkDialy.class);

        OkDialy d = new OkDialy();
        d.setDialyDate(Utils.toDate("2009/01/01"));
        d.addItem("あああ");
        d.addItem("いいい");
        d.setMember(new Member("aaa", "gmail.com"));
        makePersistentInTx(d);
        login(("aaa"));

    }
    
    public void testRun() throws Exception {
        tx().begin();
        assertEquals(1, count(OkDialy.class));
        MockUserService mus = new MockUserService("aaa@gmail.com", "gmail.com");
        mus.register();
        tx().rollback();
        param("okDialyDate", "2009/01/01");
        
        start("/okdialy/delete");
        DeleteController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertNull(getDestinationPath());
        assertEquals(0, count(OkDialy.class));
    }
}
