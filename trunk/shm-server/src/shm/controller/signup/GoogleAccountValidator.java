package shm.controller.signup;

import org.slim3.util.ApplicationMessage;

import com.google.appengine.api.users.User;

import shm.common.SingleItemValidator;
import shm.common.user.UserServiceUtil;
import shm.dao.MemberDao;

public class GoogleAccountValidator extends SingleItemValidator {

    private MemberDao dao;
    
    public GoogleAccountValidator(MemberDao dao) {
        super();
        this.dao = dao;
    }

    public GoogleAccountValidator(String message) {
        super(message);
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
