package shm.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

public class IndexController extends Controller {

    static String HOME_URL_KEY = "homeUrl";
    @Override
    public Navigation run() {
        return redirect(getRedirectUrl());
    }
    
    private String getRedirectUrl() {
        String url = application.getInitParameter(HOME_URL_KEY);
        if (StringUtil.isEmpty(url)) {
            String msg = "initial parameter [" + HOME_URL_KEY + "] not found.";
            throw new IllegalStateException(msg);
        }
        return url;
    }
}
