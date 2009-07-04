package shm.controller.okdialy;

import shm.common.MyController;
import shm.dao.OkDialyDao;

public abstract class OkDialyController extends MyController {

    protected OkDialyDao okDialyDao = new OkDialyDao();
    
}
