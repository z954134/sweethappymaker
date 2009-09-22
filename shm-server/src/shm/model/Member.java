package shm.model;

import java.util.ArrayList;
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

    /** パスワード */
    @Persistent
    private String password;
    
    /** Google ユーザー */
    @Persistent
    private User user;

    /** 人生の輪 */
    @Persistent(mappedBy="member")
    private Col col;
    
    /** OK日記 */
    @Persistent(mappedBy="member")
    private List<OkDialy> okDialies;

    /** デフォルトコンストラクタ */
    public Member() {
        this(null, null, null);
    }

    public Member(String memberId, String password) {
        this(memberId,password, null);
    }
    
    public Member(String memberId, User user) {
        this(memberId, null, user);
    }
    
    public Member(String memberId, String password, User user) {
        super();
        this.memberId = memberId;
        this.password = password;
        this.user = user;
        col = new Col();
        col.setMember(this);
    }
    
    
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
//        setMemberId(user.getNickname());
    }

    public Col getCol() {
        return col;
    }

    public void setCol(Col col) {
        this.col = col;
    }

    public List<OkDialy> getOkDialies() {
        if (okDialies == null) {
            setOkDialies(new ArrayList<OkDialy>());
        }
        return okDialies;
    }

    public void setOkDialies(List<OkDialy> okDialies) {
        this.okDialies = okDialies;
    }
    
    public boolean isValidPassword(String password) {
        return getPassword().equals(password);
    }
    
    public boolean isGoogleUser() {
        return getUser() != null;
    }
}    
