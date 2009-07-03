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

import com.google.appengine.api.users.User;

/**
 * メンバー
 * @author Tsuyoshi
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
@Version(strategy = VersionStrategy.VERSION_NUMBER)
public class Member {

    public Member() {
        super();
        setOkDialyList(new ArrayList<OkDialy>());
    }
    
    /** 主キー */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")    
    private String key;
    
    /** メンバーID */
    @Persistent
    private String memberId = "";
    
    /** Google ユーザー */
    @Persistent
    private User user;

    /** OK日記 */
    @Persistent(mappedBy="member")
    private List<OkDialy> okDialyList;
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OkDialy> getOkDialyList() {
        return okDialyList;
    }

    public void setOkDialyList(List<OkDialy> okDialyList) {
        if (okDialyList == null) {
            setOkDialyList(new ArrayList<OkDialy>(0));
            return;
        }
        this.okDialyList = okDialyList;
    }

    public void addOkDialy(OkDialy okDialy) {
        okDialy.setMember(this);
        getOkDialyList().add(okDialy);
    }

}
