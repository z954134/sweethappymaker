package shm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
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

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
@Version(strategy = VersionStrategy.VERSION_NUMBER)
public class Col implements Serializable {

    private static final int MAX_SIZE = 8;
    
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(Col.class.getName());

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String key;

    @Persistent
    private User user;
    
    @Persistent
    private List<Integer> scores;
    
    @Persistent
    private Date lastUpdate;
    
    @Persistent
    private Integer mostImportant;
    
    @Persistent
    private String nextAction;
    
    public Col() {
    }
    
    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public List<Integer> getScores() {
        return scores == null ? emptyScores() : scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date date) {
        this.lastUpdate = date;
    }
    
    public void setLastUpdate() {
        setLastUpdate(new Date());
    }

    public Integer getMostImportant() {
        return mostImportant == null ? new Integer(0) : mostImportant;
    }

    public void setMostImportant(Integer mostImportant) {
        this.mostImportant = mostImportant;
    }

    public String getNextAction() {
        return nextAction == null ? "" : nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }
    
    public String getLastUpdateText() {
        return lastUpdate == null ? "" : Utils.toString(lastUpdate);
    }

    /**
     * @return the version
     */
    public long getVersion() {
        return (Long) JDOHelper.getVersion(this);
    }
    
    private List<Integer> emptyScores() {
        List<Integer> s = new ArrayList<Integer>(MAX_SIZE);
        for (int i = 0; i < MAX_SIZE; i++) {
            s.add(new Integer(0));
        }
        return s;
    }
}
