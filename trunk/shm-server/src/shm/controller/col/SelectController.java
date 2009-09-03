package shm.controller.col;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.controller.member.MemberController;
import shm.dao.ColDao;
import shm.model.Col;
import shm.model.Member;

public class SelectController extends MemberController {

    private ColDao colDao = new ColDao();
    
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(SelectController.class.getName());

    @Override
    public Navigation run() {
        begin();
        Member member = getMember();
        
        Col col = colDao.find(member);
        if (col == null) {
            col = new Col();
        }
        
        requestScope("col", col);
        return forward("select.jsp");
    }
}
