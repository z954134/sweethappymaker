package shm.test;

import java.util.ArrayList;
import java.util.List;

import org.slim3.util.DateUtil;

import shm.model.Member;
import shm.model.OkDialy;

public class Importer extends MyJDOControllerTestCase {

    @Override
    protected void setUp() throws Exception {
        System.setProperty("slim3.controllerPackage", "shm.controller");
        super.setUp();
    }
    public void test() throws Exception {
        tx.begin();
        deleteAll(new Class[] { Member.class, OkDialy.class });
        importData();
        tx.commit();
    }
    private void importData() throws Exception {
        Member m = newMember();
        OkDialy d = newOkDialy();
        m.addOkDialy(d);
        pm.makePersistent(m);
    }
    
    private Member newMember() throws Exception {
        Member m = new Member();
        m.setMemberId("guest");
        m.setPassword("secret");
        m.setEmail("");
        return m;
    }
    
    private OkDialy newOkDialy() throws Exception {
        OkDialy okDialy = new OkDialy();
        okDialy.setDialyDate(DateUtil.toDate("2009-06-22"));
        List<String> items = new ArrayList<String>(10);
        items.add("いいこと１");
        items.add("いいこと２");
        
        okDialy.setItems(items);
        return okDialy;
    }

}
