package shm.controller;

import org.slim3.controller.Navigation;


public class IndexController extends MyController {
    
    @Override
    public Navigation run() {
        return redirect("/login");
    }
}
