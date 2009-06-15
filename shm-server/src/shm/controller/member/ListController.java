package shm.controller.member;

import java.util.ArrayList;
import java.util.List;


import org.slim3.controller.JDOController;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanMap;
import org.slim3.util.BeanUtil;

import shm.model.Member;



public class ListController extends JDOController {

    @Override
    public Navigation run() {
        List<Member> list = from(Member.class).getResultList();
        List<BeanMap> userList = new ArrayList<BeanMap>(list.size());
        for (Member u : list) {
            BeanMap m = new BeanMap();
            BeanUtil.copy(u, m);
            userList.add(m);
        }
        requestScope("memberList", userList);
        return forward("/member/list.jsp");
    }
}
