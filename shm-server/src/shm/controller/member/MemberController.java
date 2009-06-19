package shm.controller.member;


import shm.common.MyJDOController;
import shm.dao.MemberDao;

/**
 * @author Tsuyoshi
 *
 */
public abstract class MemberController extends MyJDOController {
    
    /** メンバーデータアクセス */
    protected MemberDao dao;
    
    @Override
    protected void setUp() {
        super.setUp();
        dao = new MemberDao(pm);
    }

}
