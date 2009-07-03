package shm.dao;

import com.google.appengine.api.users.User;

import shm.model.Member;
import shm.test.MyJDOControllerTestCase;

public class MemberDaoTest extends MyJDOControllerTestCase {

    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllInTx(Member.class);
        Member member = new Member();
        member.setMemberId("aaa");
        member.setUser(new User("aaa@gmail.com", "gmail.com"));
        
        makePersistentInTx(member);
    }
    
    public void testGetMemberByUser() {
        
        MemberDao target = new MemberDao();
        User user = new User("aaa@gmail.com", "gmail.com");
        Member actual = target.findMember(user);
        assertNotNull(actual);
        assertEquals("aaa", actual.getMemberId());
    }

}
