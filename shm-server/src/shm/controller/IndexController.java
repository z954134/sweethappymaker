package shm.controller;

import org.slim3.controller.Navigation;

import shm.cool.common.MyController;

public class IndexController extends MyController {
    
    @Override
    public Navigation run() {
        return redirect("/login");
    }
}
