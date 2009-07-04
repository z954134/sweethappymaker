package shm.common.user;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

public class UserServiceUtil {

    private static UserServiceFactory factoryInstance = new GoogleUserServiceFactory();

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
    
    static void setUserServiceFactory(UserServiceFactory factory) {
        factoryInstance = factory; 
    }
    
    
}
