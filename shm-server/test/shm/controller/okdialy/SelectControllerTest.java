package shm.controller.okdialy;

import java.util.ArrayList;
import java.util.List;

import org.slim3.util.BeanMap;
import org.slim3.util.BeanUtil;
import org.slim3.util.DateUtil;

import shm.controller.member.LoginController;
import shm.model.Member;
import shm.model.OkDialy;
import shm.test.MyJDOControllerTestCase;

public class SelectControllerTest extends MyJDOControllerTestCase {
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Member m = new Member(); 
        deleteAllInTx(new Class[] { Member.class, OkDialy.class });
        
        m.setMemberId("aaa");
        m.setEmail("aaa@aaa.com");
        OkDialy okDialy = new OkDialy();
        okDialy.setDialyDate(DateUtil.toDate("2009-01-22"));
        List<String> items = new ArrayList<String>();
        items.add("いいこと１");
        items.add("いいこと２");
        okDialy.setItems(items);
        
        m.addOkDialy(okDialy);
        m.addOkDialy(okDialy);
        makePersistentInTx(m);
        refreshPersistenceManager();
        
    }
    public void testRun() throws Exception {
        sessionScope(LoginController.LOGIN_MEMBER_KEY, "aaa");
        start("/okdialy/select");
        SelectController controller = getController();
        assertNotNull(controller);
        assertFalse(isRedirect());
        assertEquals("/okdialy/select.jsp", getNextPath());
        BeanMap map = requestScope("okDialy");
        assertNotNull(map);
        OkDialy okDialy = new OkDialy();
        BeanUtil.copy(map, okDialy);
        assertEquals("2009-01-22", okDialy.getDialyDate());
        List<String> items = okDialy.getItems();
        assertEquals(2, items.size());
    }
}
