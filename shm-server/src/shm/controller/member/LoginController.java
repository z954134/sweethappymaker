package shm.controller.member;

import org.slim3.controller.Navigation;

import shm.common.Const;
import shm.model.Member;

public class LoginController extends MemberController {

    @Override
    public Navigation run() {

        String memberId = requestScope("memberId");
        String password = requestScope("password");

        // メンバーIDよりエンティティを取得する
        Member member = memberDao.getMemberByMemberId(memberId);
        if (member == null || !member.isValidPassword(password)) {
            // メンバーが存在しない（メンバーID誤り）
            // またはパスワード誤り
            String msg = "メンバーIDまたはパスワードが違います。";
            saveMessages(msg);
            return forwardBase("failure.jsp");
        }
        // セッションにログイン情報を格納する
        sessionScope(Const.LOGIN_MEMBER_ID, member.getMemberId());
        return forwardBase("success.jsp");
    }
}
