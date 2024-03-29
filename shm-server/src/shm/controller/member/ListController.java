package shm.controller.member;

import java.util.List;

import org.slim3.controller.Navigation;

import shm.model.Member;

public class ListController extends MemberController {

    @Override
    public Navigation runInTx() {

        List<Member> memberList = memberDao.findAll();
        requestScope("memberList", memberList);
        return forward("list.jsp");
    }
}
