package shm.controller.okdialy;

import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.model.Member;
import shm.model.OkDialy;

public class ListController extends OkDialyController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(ListController.class.getName());

    @Override
    public Navigation run() {
        begin();
        Member member = getLoginMemberFromSession();
        List<OkDialy> okDialyList = member.getOkDialyList();
        
        requestScope("okDialyList", detachAndCopy(okDialyList));
        return forwardBase("list.jsp");
    }
}
