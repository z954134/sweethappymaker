package shm.model;

import java.util.ArrayList;
import java.util.Date;
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
public class OkDialy {
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")     
    private String key;
    
    @Persistent
    private Date dialyDate;
    
    @Persistent
    private List<String> items = new ArrayList<String>(10);
    
    @Persistent(mappedBy = "okDialyList")
    private Member member;

    
    public void addItem(String item) {

        if (items.size() == 10) {
            throw new IllegalStateException();
        }
        items.add(item == null ? "" : item);
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getDialyDate() {
        return dialyDate;
    }

    public void setDialyDate(Date dialyDate) {
        this.dialyDate = dialyDate;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = (items == null ? new ArrayList<String>(10) : items);
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
