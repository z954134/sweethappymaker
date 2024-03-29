package shm.controller.member;


import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import shm.model.Member;

public class InsertController extends MemberController {
    
    @Override
    public Navigation run() {
        String memberId = requestScope("memberId");
        if (memberDao.exists(memberId)) {
            String msg =
            "メンバーID [" + memberId + "] は既に存在します。"
                + "別のメンバーIDを指定してください。";
            saveMessages(msg);
            return forward("failure.jsp");
        }
        
        Member member = new Member();
        BeanUtil.copy(request, member);
        memberDao.makePersistentInTx(member);
        
        return forward("success.jsp");
    }
}
