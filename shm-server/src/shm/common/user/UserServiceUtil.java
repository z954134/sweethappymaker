package shm.common.user;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

public class UserServiceUtil {

    private static final UserServiceFactory DEFAULT_FACTORY = new GoogleUserServiceFactory();
    private static UserServiceFactory factoryInstance = DEFAULT_FACTORY;

    private static final User GUEST = new User("GUEST@gmail.com", "gmail.com"); 
    
    public static UserService getUserService() {
        return factoryInstance.getUserService();
    }
    
    public static User getCurrentUser() {
        User user = getUserService().getCurrentUser();
        if (user == null) {
            user = GUEST;
        }
        return user;
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
