package shm.controller.signup;

import org.slim3.util.ApplicationMessage;

import shm.cool.common.SingleItemValidator;
import shm.cool.common.user.UserServiceUtil;
import shm.dao.MemberDao;

import com.google.appengine.api.users.User;

public class UserValidator extends SingleItemValidator {

    public UserValidator() {
        super();
    }

    public UserValidator(String message) {
        super(message);
    }

    @Override
    protected boolean isValid(Object param) {
        MemberDao dao = new MemberDao();
        User user = UserServiceUtil.getCurrentUser();
        // メンバーIDが既に存在する場合はNG
        if (user != null && dao.exists(user)) {
            return false;
        }
        return true;
    }

    @Override
    protected String getDefaultMessage(Object param, String name) {
        return ApplicationMessage.get("validator.user.already-registered", param);
    }

}
