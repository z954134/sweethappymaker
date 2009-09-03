package shm.controller.okdialy;

import java.util.Date;
import java.util.logging.Logger;

import org.slim3.controller.Navigation;

import shm.common.Utils;
import shm.model.Member;
import shm.model.OkDialy;

public class DeleteController extends OkDialyController {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(DeleteController.class.getName());

    @Override
    public Navigation runInTx() {
        Member member = getMember();
        Date dialyDate = Utils.toDate(requestScope("okDialyDate"));
        OkDialy okDialy = okDialyDao.find(member, dialyDate);
        okDialyDao.deletePersistent(okDialy);
        return null;
    }
}
