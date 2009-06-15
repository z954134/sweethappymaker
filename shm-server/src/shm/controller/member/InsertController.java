package shm.controller.member;


import org.slim3.controller.JDOController;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import shm.model.Member;

public class InsertController extends JDOController {
    
    @Override
    public Navigation run() {
        Member user = new Member();
        BeanUtil.copy(request, user);
        makePersistentInTx(user);
        return null;
    }
}
