package shm.controller.okdialy;

import shm.controller.member.MemberController;
import shm.dao.OkDialyDao;

public abstract class OkDialyController extends MemberController {

    protected OkDialyDao okDialyDao = new OkDialyDao();
    
}
