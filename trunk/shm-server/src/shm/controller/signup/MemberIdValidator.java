package shm.controller.signup;

import org.slim3.util.ApplicationMessage;

import shm.cool.common.SingleItemValidator;
import shm.dao.MemberDao;

public class MemberIdValidator extends SingleItemValidator {

    private MemberDao dao;
    
    public MemberIdValidator(MemberDao dao) {
        this.dao = dao;
    }
    
//    public MemberIdValidator() {
//        super();
//    }

    @Override
    protected boolean isValid(Object param) {

        String memberId = param.toString();
        // メンバーIDが既に存在する場合はNG
        if (dao.exists(memberId)) {
            return false;
        }
        return true;
    }

    @Override
    protected String getDefaultMessage(Object param, String name) {
        return ApplicationMessage.get("validator.memberId.duplicated", param);
    }

}
