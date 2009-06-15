package shm.controller.member;


import org.slim3.controller.JDOController;
import org.slim3.controller.Navigation;

import shm.model.Member;

public class DeleteController extends JDOController {
    
    @Override
    public Navigation run() {
        tx.begin();
        
        Member user = pm.getObjectById(Member.class, requestScope("key"));
        pm.deletePersistent(user);
        
        tx.commit();
        return null;
    }
}
