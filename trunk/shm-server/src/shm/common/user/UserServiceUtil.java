package shm.common.user;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

public class UserServiceUtil {

    private static final UserServiceFactory DEFAULT_FACTORY = new GoogleUserServiceFactory();
    private static UserServiceFactory factoryInstance = DEFAULT_FACTORY;

    
    public static UserService getUserService() {
        return factoryInstance.getUserService();
    }
    
    public static User getCurrentUser() {
        User user = getUserService().getCurrentUser();
        return user;
    }
    
    public static boolean isLoggedIn() {
        return getCurrentUser() != null;
    }
    
    public static void resetUserServiceFactory() {
        factoryInstance = DEFAULT_FACTORY;
    }
    
    static void setUserServiceFactory(UserServiceFactory factory) {
        factoryInstance = factory; 
    }

    private UserServiceUtil() {
    }
}
