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


@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
@Version(strategy = VersionStrategy.VERSION_NUMBER)
public class Member {

    /** 主キー */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")    
    private String key;
    
    /** 名前 */
    @Persistent
    private String name;
    
    /** Eメールアドレス */
    @Persistent
    private String email;


    /** OK日記 */
    @Persistent(mappedBy="member")
    private List<OkDialy> okDialyList = new ArrayList<OkDialy>();
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        okDialyList.add(okDialy);
    }
}
