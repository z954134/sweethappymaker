package shm.model;

import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;

import shm.common.Utils;

import com.google.appengine.api.users.User;

/**
 * メンバー
 * @author Tsuyoshi
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
@Version(strategy = VersionStrategy.VERSION_NUMBER)
public class Member {


    /** 主キー */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")    
    private String key;
    
    /** メンバーID */
    @Persistent
    private String memberId;
    
    @Persistent
    private String password;
    
    /** Google ユーザー */
    @Persistent
    private User user;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMemberId() {
        if (memberId == null) {
            return user == null ? "" : user.getNickname();
        }
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public String getPassword() {
        return Utils.convertIfNull(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        setMemberId(user.getNickname());
    }

    public boolean isValidPassword(String password) {
        return getPassword().equals(password);
    }
}    

