package shm.controller.member;

import org.slim3.controller.JDOController;
import org.slim3.controller.Navigation;

import shm.model.Member;
import shm.model.MemberMeta;

public class LoginController extends JDOController {

    public static final String LOGIN_MEMBER_KEY = "memberKey";

    @Override
    public Navigation run() {

        String memberId = requestScope("memberId");
        String password = requestScope("password");

        // メンバーIDよりエンティティを取得する
        Member member = getMemberByMemberId(memberId);

        if (member == null || !member.isValidPassword(password)) {
            // メンバーが存在しない（メンバーID誤り）
            // またはパスワード誤り
            return forward(basePath + "login_failure.jsp");
        }
        // セッションにログイン情報を格納する
        sessionScope(LOGIN_MEMBER_KEY, member.getKey());
        return forward(basePath + "login_success.jsp");
    }
    
    private Member getMemberByMemberId(String memberId) {
        MemberMeta m = new MemberMeta();
        Member member =
            from(Member.class).where(m.memberId.eq(memberId)).getSingleResult();
        return member;
    }
}
