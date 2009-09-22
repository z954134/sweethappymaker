package shm.cool.common.user;

import com.google.appengine.api.users.UserService;

public interface UserServiceFactory {
    
    UserService getUserService();
}

