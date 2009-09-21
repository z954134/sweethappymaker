package shm.controller.login;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Errors;
import org.slim3.controller.validator.Validators;

import shm.controller.member.MemberController;
import shm.model.Member;

public class LoginController extends MemberController {

    @SuppressWarnings("unused")
    private static final Logger logger =
        Logger.getLogger(LoginController.class.getName());

    @Override
    public Navigation run() {
        Errors errors = validate();
        if (errors != null && !errors.isEmpty()) {
            return forward("index");
        }
        String memberId = requestScope("memberId");
        String password = requestScope("password");
        if (!isValidIdAndPassword(memberId, password)) {
            String msg = "メンバーIDかパスワードに誤りがあります。";
            errors.put("memberId", msg);
            errors.put("password", msg);
            return forward("index");
        }
        // ログイン成功
        sessionScope("memberId", memberId);
        return redirect("/flex-bin/main.html");
    }

    protected Errors validate() {
        Validators v = new Validators(request);
        v.add("memberId", v.required());
        v.add("password", v.required(), v.minlength(8));
        v.validate();
        return v.getErrors();
        
    }

    protected boolean isValidIdAndPassword(String memberId, String password) {
        Member member = memberDao.findMember(memberId);
        return member != null && member.isValidPassword(password);
    }
}
