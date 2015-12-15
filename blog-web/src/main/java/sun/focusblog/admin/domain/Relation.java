package sun.focusblog.admin.domain;

import java.util.Date;

/**
 * Created by root on 2015/12/15.
 * <p/>
 * Relation entity
 */
public class Relation {
    private String userId;
    private String followerId;
    private int type;   // 1 follow, 2 blacklist
    private Date createTime;

    public Relation() {
    }

    public Relation(String userId, String followerId){
        this.userId = userId;
        this.followerId = followerId;
    }

    public Relation(String userId, String followerId, int type) {
        this.userId = userId;
        this.followerId = followerId;
        this.type = type;
        createTime = new Date();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
