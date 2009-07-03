package shm.common.user;

import com.google.appengine.api.users.UserService;

public class GoogleUserServiceFactory implements UserServiceFactory {

    @Override
    public UserService getUserService() {
        return com.google.appengine.api.users.UserServiceFactory.getUserService();
    }

}
