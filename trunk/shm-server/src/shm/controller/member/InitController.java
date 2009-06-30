package shm.controller.member;

import org.slim3.controller.Navigation;

import shm.model.Member;

public class InitController extends MemberController {

    @Override
    public Navigation runInTx() {
        
        Member guest = new Member();
        guest.setMemberId("guest");
        guest.setPassword("guest");
        guest.setEmail("");
        createMemberIfNotExists(guest);

        Member admin = new Member();
        admin.setMemberId("admin");
        admin.setPassword("poohchang");
        admin.setEmail("");
        createMemberIfNotExists(admin);
        
        return null;
    }

    private void createMemberIfNotExists(Member member) {
        if (!memberDao.exists(member.getMemberId())) {
            memberDao.makePersistent(member);
        }
    }
}
