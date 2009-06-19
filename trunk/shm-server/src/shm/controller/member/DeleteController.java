package shm.controller.member;


import org.slim3.controller.Navigation;

public class DeleteController extends MemberController {
    
    @Override
    protected Navigation runInTx() {
        dao.deleteObjectById(requestScope("key"));
        return null;
    }
}
