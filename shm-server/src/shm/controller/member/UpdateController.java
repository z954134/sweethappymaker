package shm.controller.member;


import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import shm.model.Member;

public class UpdateController extends MemberController {

    @Override
    public Navigation runInTx() {
        // メンバーID取得
        String memberId = requestScope("memberId");

        // Keyから更新前のメンバー取得
        Member member = memberDao.find(requestScope("key"));
        
        // メンバーIDの変更確認
        if (!memberId.equals(member.getMemberId())) {
            // 変更後のメンバーID存在チェック
            if (memberDao.exists(memberId)) {
                saveMessages("メンバーID [" + memberId + "] は既に存在します。"
                    + "別のメンバーIDを指定してください。");
                return forwardBase("failure.jsp");
            }
        }
        BeanUtil.copy(request, member);
        memberDao.makePersistent(member);
        return forwardBase("success.jsp");
    }
}
