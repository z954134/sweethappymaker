package shm.controller.signup;

import org.slim3.util.ApplicationMessage;

import com.google.appengine.api.users.User;

import shm.cool.common.SingleItemValidator;
import shm.cool.common.user.UserServiceUtil;
import shm.dao.MemberDao;

public class GoogleAccountValidator extends SingleItemValidator {

    private MemberDao dao;
    
    public GoogleAccountValidator(MemberDao dao) {
        super();
        this.dao = dao;
    }

    @Override
    protected boolean isValid(Object param) {
        User user = UserServiceUtil.getCurrentUser();
        // メンバーIDが既に存在する場合はNG
        if (dao.exists(user)) {
            return false;
        }
        return true;
    }

    @Override
    protected String getDefaultMessage(Object param, String name) {
        return ApplicationMessage.get("validator.user.already-registered", param);
    }

}
