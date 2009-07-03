package shm.common.user;

import com.google.appengine.api.users.UserService;

public class UserServiceUtil {

    private static UserServiceFactory factoryInstance = new GoogleUserServiceFactory();

    public static UserService getUserService() {
        return factoryInstance.getUserService();
    }
    
    static void setUserServiceFactory(UserServiceFactory factory) {
        factoryInstance = factory; 
    }
}
