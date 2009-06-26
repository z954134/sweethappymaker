package shm.controller.okdialy;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.common.MyJDOController;
import shm.dao.OkDialyHintDao;
import shm.model.OkDialyHint;

public class HintController extends MyJDOController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(HintController.class.getName());

    
    private OkDialyHintDao dao; 
    
    @Override
    protected void setUp() {
        super.setUp();
        dao = new OkDialyHintDao(pm);
    }

    @Override
    public Navigation run() {
        OkDialyHint nextHint = getNextHint(asString("previousHintKey"));
        requestScope("hint", nextHint.getHint());
        return forwardBase("hint.jsp");
    }
    
    private OkDialyHint getNextHint(String previousHintKey) {
        List<OkDialyHint> hints = dao.findAll();
        int cnt = hints.size();
        
        Random rand = new Random(System.currentTimeMillis());
        OkDialyHint nextHint = null;
        do {
            int next = rand.nextInt(cnt);
            nextHint = hints.get(next);
            if (!nextHint.getKey().equals(previousHintKey)) {
                break;
            }
        } while(true);
        return nextHint;
    }
}
