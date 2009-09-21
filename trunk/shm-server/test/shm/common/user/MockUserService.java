package shm.common.user;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

public class MockUserService implements UserService {

    public User user;
    public boolean isUserAdmin;
    public boolean isUserLoggedIn;

    public MockUserService() {
    }

    public MockUserService(String email, String authDomain) {
        this(email, authDomain, false, true);
    }

    public MockUserService(String email, String authDomain, boolean isUserAdmin) {
        this(new User(email, authDomain), isUserAdmin, true);
    }

    public MockUserService(String email, String authDomain,
            boolean isUserAdmin, boolean isUserLoggedIn) {
        this(new User(email, authDomain), isUserAdmin, isUserLoggedIn);
    }
    
    
    public MockUserService(User user) {
        this(user, false, true);
    }

    
    public MockUserService(User user, boolean isUserAdmin) {
        this(user, isUserAdmin, true);
    }
    
    public MockUserService(User user, boolean isUserAdmin,
            boolean isUserLoggedIn) {
        this.user = user;
        this.isUserAdmin = isUserAdmin;
        this.isUserLoggedIn = isUserLoggedIn;
    }

    public void register() {
        MockUserServiceFactory factory = new MockUserServiceFactory(this);
        UserServiceUtil.setUserServiceFactory(factory);
    }

    public String createLoginURL(String destinationURL) {
        return destinationURL;
    }


    public String createLogoutURL(String destinationURL) {
        return destinationURL;
    }


    public User getCurrentUser() {
        return user;
    }


    public boolean isUserAdmin() {
        return isUserAdmin;
    }


    public boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }

 
    public String createLoginURL(String destinationURL, String authDomain) {
        return destinationURL;
    }


    public String createLogoutURL(String destinationURL, String authDomain) {
        return destinationURL;
    }

}
