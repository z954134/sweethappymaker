package shm.controller;

import org.slim3.controller.Navigation;

import shm.common.MyController;

public class IndexController extends MyController {

    static String HOME_URL_KEY = "homeUrl";

    @Override
    public Navigation run() {
        String redirectUrl = getRedirectUrl();
        return redirect(redirectUrl);
    }

    private String getRedirectUrl() {
        String url =
            isDevelopment()
                ? "/flex-bin-debug/main.html"
                : "/flex-bin/main.html";
        return url;
    }
}
