package shm.test;

import java.util.ArrayList;
import java.util.List;

import org.slim3.tester.JDOTestCase;
import org.slim3.util.DateUtil;

import shm.model.Member;
import shm.model.OkDialy;

public class Importer extends JDOTestCase {

    public void test() throws Exception {

        importData();
        
    }
    private void importData() throws Exception {
        Member m = newMember();
        OkDialy d = newOkDialy();
        m.addOkDialy(d);
        makePersistentInTx(m);
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
