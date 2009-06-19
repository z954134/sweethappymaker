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

import org.slim3.util.StringUtil;

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
    private String memberId = "";
    
    /** パスワード */
    @Persistent
    private String password = "";
    
    /** Eメールアドレス */
    @Persistent
    private String email = "";

    /** OK日記 */
    @Persistent(mappedBy="member")
    private List<OkDialy> okDialyList;
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<OkDialy> getOkDialyList() {
        return okDialyList;
    }

    public void setOkDialyList(List<OkDialy> okDialyList) {
        this.okDialyList = okDialyList;
    }

    public void addOkDialy(OkDialy okDialy) {
        okDialy.setMember(this);
        getOkDialyList().add(okDialy);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (StringUtil.isEmpty(password)) {
            throw new IllegalArgumentException("password must not be null or empty.");
        }
        this.password = password;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public boolean isValidPassword(String password) {
        return this.password.equals(password);
    }
}
