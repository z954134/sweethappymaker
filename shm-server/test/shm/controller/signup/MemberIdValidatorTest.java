package shm.controller.signup;

import shm.dao.MemberDao;
import shm.model.Member;
import shm.test.MyJDOControllerTestCase;

public class MemberIdValidatorTest extends MyJDOControllerTestCase {

    private MemberIdValidator target = new MemberIdValidator(new MemberDao());
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(Member.class);
        Member m = new Member();
        m.setMemberId("aaa");
        m.setPassword("12345678");
        makePersistentInTx(m);
    }
    
    
   
    public void testIsValidObject() {
        
        boolean result = target.isValid("aaa");
        assertFalse(result);
    }

}
