package shm.controller.member;


import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import shm.model.Member;

public class UpdateController extends MemberController {

    @Override
    public Navigation run() {
        String memberId = requestScope("memberId");
        

        tx.begin();
        // 更新前のメンバー取得
        Member member = pm.getObjectById(Member.class, requestScope("key"));
        // メンバーIDの変更確認
        if (!memberId.equals(member.getMemberId())) {
            // 変更後のメンバーID存在チェック
            if (exists(memberId)) {
                List<String> messageList = new ArrayList<String>();
                messageList.add("メンバーID [" + memberId + "] は既に存在します。"
                    + "別のメンバーIDを指定してください。");
                requestScope("messageList", messageList);
                return forwardBase("failure.jsp");
            }
        }
        
        BeanUtil.copy(request, member);
        pm.makePersistent(member);
        
        tx.commit();
        return forwardBase("success.jsp");
    }
}
