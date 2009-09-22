package shm.controller.login;

import java.util.Map;

import org.slim3.controller.validator.AbstractValidator;
import org.slim3.util.ApplicationMessage;

import shm.controller.member.MemberController;
import shm.dao.MemberDao;
import shm.model.Member;

public class MemberIdValidator extends AbstractValidator {

    public MemberIdValidator() {
        super();
    }

    public MemberIdValidator(String message) {
        super(message);
    }

    public String validate(Map<String, Object> parameters, String name) {
      MemberDao dao = new MemberDao();
      String memberId = parameters.get(MemberController.MEMBER_ID_KEY).toString();
      String password = parameters.get(MemberController.MEMBER_ID_KEY).toString();
      Member member = dao.findMember(memberId);
      if (member == null || member.isValidPassword(password)) {
          return ApplicationMessage.get("validator.memberId", "");
      }
      return null;
      
    }
}
