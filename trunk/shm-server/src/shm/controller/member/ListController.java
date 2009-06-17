package shm.controller.member;

import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanMap;

import shm.common.MyJDOController;
import shm.model.Member;



public class ListController extends MyJDOController {

    @Override
    public Navigation run() {
        List<Member> entityList = from(Member.class).getResultList();
        List<BeanMap> memberList = copy(entityList);
        requestScope("memberList", memberList);
        return forwardBase("list.jsp");
    }
}
