package shm.controller.member;

import org.slim3.controller.Navigation;

import shm.model.Member;

public class LoginController extends MemberController {

    public static final String LOGIN_MEMBER_KEY = "memberKey";

    @Override
    public Navigation run() {

        String memberId = requestScope("memberId");
        String password = requestScope("password");

        // メンバーIDよりエンティティを取得する
        Member member = dao.getMemberByMemberId(memberId);
        if (member == null || !member.isValidPassword(password)) {
            // メンバーが存在しない（メンバーID誤り）
            // またはパスワード誤り
            return forwardBase("login_failure.jsp");
        }
        // セッションにログイン情報を格納する
        sessionScope(LOGIN_MEMBER_KEY, member.getKey());
        return forwardBase("login_success.jsp");
    }
}
