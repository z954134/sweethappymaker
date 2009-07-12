package shm.controller.col;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.common.MyController;
import shm.common.user.UserServiceUtil;
import shm.dao.ColDao;
import shm.model.Col;

import com.google.appengine.api.users.User;

public class SaveController extends MyController {

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
        User user = UserServiceUtil.getCurrentUser();
        Col col = colDao.find(user);
        if (col == null) {
            col = new Col();
            col.setUser(user);
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
