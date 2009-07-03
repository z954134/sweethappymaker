package shm.controller.member;

import org.slim3.controller.Navigation;

import shm.model.Member;

public class InitController extends MemberController {

    @Override
    public Navigation runInTx() {
        
        Member guest = new Member();
        guest.setMemberId("guest");
        createMemberIfNotExists(guest);
        return null;
    }

    private void createMemberIfNotExists(Member member) {
        if (!memberDao.exists(member.getMemberId())) {
            memberDao.makePersistent(member);
        }
    }
}
