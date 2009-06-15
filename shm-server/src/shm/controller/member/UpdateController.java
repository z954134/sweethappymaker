package shm.controller.member;


import org.slim3.controller.JDOController;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import shm.model.Member;

public class UpdateController extends JDOController {

    @Override
    public Navigation run() {
        tx.begin();

        Member user = pm.getObjectById(Member.class, requestScope("key"));
        BeanUtil.copy(request, user);
        pm.makePersistent(user);
        
        tx.commit();
        return null;
    }
}
