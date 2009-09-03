package shm.controller.col;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.controller.member.MemberController;
import shm.dao.ColDao;
import shm.model.Col;
import shm.model.Member;

public class SaveController extends MemberController {

    private ColDao colDao = new ColDao();

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(SaveController.class.getName());

    @Override
    public Navigation runInTx() {
        
        Col col = getCol();
        col.setMostImportant(asInteger("mostImportant"));
        col.setNextAction(asString("nextAction"));
        col.setScores(createScoresFromRequest());
        col.setLastUpdate();
        colDao.makePersistent(col);

        return null;
    }

    private Col getCol() {
        Member member = getMember();
        Col col = colDao.find(member);
        if (col == null) {
            col = new Col();
            col.setMember(member);
        }
        return col;
    }

    private List<Integer> createScoresFromRequest() {

        List<Integer> scores = new ArrayList<Integer>();
        String[] params = paramValues("score");
        if (params != null) {
            for (String p : params) {
                Integer score = Integer.parseInt(p);
                scores.add(score);
            }
        }
        return scores;

    }
}
