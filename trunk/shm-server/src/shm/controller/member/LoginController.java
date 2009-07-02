package shm.controller.member;

import org.slim3.controller.Navigation;

import shm.model.Member;

public class LoginController extends MemberController {

    @Override
    public Navigation run() {

        String memberId = getMemberIdInRequest();
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
        // セッションをリセット
        resetSession();
        // セッションにログイン情報を格納する
        sessionScope(MEMBER_ID_KEY, member.getMemberId());
        return forwardBase("success.jsp");
    }
    
    private void resetSession() {
        request.getSession(true).invalidate();
    }
}
