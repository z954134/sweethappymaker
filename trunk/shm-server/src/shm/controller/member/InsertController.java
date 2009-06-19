package shm.controller.member;


import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import shm.model.Member;

public class InsertController extends MemberController {
    
    @Override
    public Navigation run() {
        String memberId = requestScope("memberId");
        if (dao.exists(memberId)) {
            List<String> messageList = new ArrayList<String>();
            messageList.add("メンバーID [" + memberId + "] は既に存在します。"
                + "別のメンバーIDを指定してください。");
            requestScope("messageList", messageList);
            return forwardBase("failure.jsp");
        }
        
        Member user = new Member();
        BeanUtil.copy(request, user);
        makePersistentInTx(user);
        return forwardBase("success.jsp");
    }
}
