package shm.controller.signup;

import org.slim3.util.ApplicationMessage;

import shm.common.SingleItemValidator;
import shm.dao.MemberDao;

public class MemberIdValidator extends SingleItemValidator {

    public MemberIdValidator() {
        super();
    }

    public MemberIdValidator(String message) {
        super(message);
    }
    

    @Override
    protected boolean isValid(Object param) {
      MemberDao dao = new MemberDao();      
      String memberId = param.toString();
      return !dao.exists(memberId);
    }
    
    @Override
    protected String getDefaultMessage(Object param, String name) {
        return ApplicationMessage.get("validator.memberId", param);
    }

}
