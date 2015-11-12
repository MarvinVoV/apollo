package sun.yamorn.blog.admin.domain.auth;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 2015/11/7.
 *
 * Main user entity
 */
public class User {
    private String userId;

    private String userName;

    private String password;

    private Date createDate;

    private boolean enable;

    private List<Function> functions= new LinkedList<>();

    private List<Role> roles = new LinkedList<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
