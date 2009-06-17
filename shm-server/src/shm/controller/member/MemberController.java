/**
 * 
 */
package shm.controller.member;


import shm.common.MyJDOController;
import shm.model.Member;
import shm.model.MemberMeta;

/**
 * @author Tsuyoshi
 *
 */
public abstract class MemberController extends MyJDOController {

    protected final Member getMemberByMemberId(String memberId) {
        MemberMeta m = new MemberMeta();
        Member member =
            from(Member.class).where(m.memberId.eq(memberId)).getSingleResult();
        return member;
    }

    protected final boolean exists(String memberId) {
        Member m = getMemberByMemberId(memberId);
        return m != null;
    }
}
