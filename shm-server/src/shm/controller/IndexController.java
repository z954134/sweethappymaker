package shm.controller;

import org.slim3.controller.Navigation;

import shm.common.MyController;

public class IndexController extends MyController {

    private static final String HOME_URL="/flex-bin/main.html";
    
    @Override
    public Navigation run() {
        String redirectUrl = getRedirectUrl();
        return redirect(redirectUrl);
    }

    private String getRedirectUrl() {
        return HOME_URL;
    }
}
