package shm.controller.member;

import java.util.List;

import org.slim3.controller.Navigation;

import shm.common.MyJDOController;
import shm.model.Member;



public class ListController extends MyJDOController {

    @Override
    public Navigation run() {
        List<Member> memberList = from(Member.class).getResultList();
        requestScope("memberList", copy(memberList));
        return forwardBase("list.jsp");
    }
}
