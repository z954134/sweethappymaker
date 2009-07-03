package shm.common.user;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

public class MockUserService implements UserService {

    public User user = null;
    public boolean isUserAdmin = false;
    public boolean isUserLoggedIn = false;
    
    public MockUserService() {
    }
    
    public MockUserService(String email, String authDomain) {
        this(email, authDomain, false, false);
    }
    
    public MockUserService(User user, boolean isUserAdmin, boolean isUserLoggedIn) {
        this.user = user;
        this.isUserAdmin = isUserAdmin;
        this.isUserLoggedIn = isUserLoggedIn;
    }
    
    public MockUserService(String email, String authDomain, boolean isUserAdmin, boolean isUserLoggedIn) {
        this(new User(email, authDomain), isUserAdmin, isUserLoggedIn);
    }
    
    
    public void register() {
        MockUserServiceFactory factory = new MockUserServiceFactory(this);
        UserServiceUtil.setUserServiceFactory(factory);
    }
    
    
    @Override
    public String createLoginURL(String destinationURL) {
        return "";
    }

    @Override
    public String createLogoutURL(String destinationURL) {
        return "";
    }

    @Override
    public User getCurrentUser() {
        return user;
    }

    @Override
    public boolean isUserAdmin() {
        return isUserAdmin;
    }

    @Override
    public boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }

}
