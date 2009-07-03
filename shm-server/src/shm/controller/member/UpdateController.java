package shm.controller.member;


import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import shm.model.Member;

public class UpdateController extends MemberController {

    @Override
    public Navigation runInTx() {
         
        // メンバーID取得
        String memberId = getMemberIdInRequest();

        // Keyから更新前のメンバー取得
        Member member = memberDao.find(requestScope("key"));
        
        // メンバーIDの変更確認
        if (!memberId.equals(member.getMemberId())) {
                saveMessages("メンバーIDの変更はできません。");
                return forwardBase("failure.jsp");
        }
        BeanUtil.copy(request, member);
        return forwardBase("success.jsp");
    }
}
