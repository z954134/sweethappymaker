package shm.controller.member;

import shm.common.MyController;
import shm.dao.MemberDao;

public abstract class MemberController extends MyController {
    protected MemberDao memberDao = new MemberDao();
}
