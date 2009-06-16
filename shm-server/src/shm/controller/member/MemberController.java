/**
 * 
 */
package shm.controller.member;

import org.slim3.controller.JDOController;
import org.slim3.controller.Navigation;

import shm.model.Member;
import shm.model.MemberMeta;

/**
 * @author Tsuyoshi
 *
 */
public abstract class MemberController extends JDOController {

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

    protected final Navigation forwardBase(String path) {
        return forward(basePath + path);
    }
}
