package shm.controller.member;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.model.Member;

public class SelectController extends MemberController {

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(SelectController.class.getName());

    @Override
    public Navigation run() {
        begin();
        Member member = getMember();
        requestScope("member", member);
        return forward("select.jsp");
    }
}
