package shm.controller.col;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;
import org.slim3.util.BeanMap;

import shm.common.MyController;
import shm.common.user.UserServiceUtil;
import shm.dao.ColDao;
import shm.model.Col;

import com.google.appengine.api.users.User;

public class SelectController extends MyController {

    private ColDao colDao = new ColDao();
    
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(SelectController.class.getName());

    @Override
    public Navigation run() {
        begin();
        User user = UserServiceUtil.getCurrentUser();
        
        Col col = colDao.find(user);
        if (col == null) {
            col = new Col();
        }
        BeanMap m = detachAndCopy(col);
        requestScope("col", m);
        return forward("select.jsp");
    }
}
