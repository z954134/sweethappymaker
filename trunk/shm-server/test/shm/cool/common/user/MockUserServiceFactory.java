package shm.cool.common.user;

import shm.cool.common.user.UserServiceFactory;

import com.google.appengine.api.users.UserService;

public class MockUserServiceFactory implements UserServiceFactory {

    private UserService userService;

    public MockUserServiceFactory(UserService userService) {
        this.userService = userService;
    }
    

    public UserService getUserService() {
        return userService;
    }
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
