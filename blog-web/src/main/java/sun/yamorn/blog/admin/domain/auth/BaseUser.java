package sun.yamorn.blog.admin.domain.auth;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 2015/11/7.
 */
public class BaseUser implements IUser {

    protected String userId;

    protected String userName;

    protected String password;

    protected Date createDate;

    protected boolean enable;

    protected List<IFunction> functions= new LinkedList<>();

    protected List<IRole> roles = new LinkedList<>();

    @Override
    public String getUserId() {
        return this.userId;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Date getCreateDate() {
        return this.createDate;
    }

    @Override
    public Boolean isEnable() {
        return this.enable;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public List<IFunction> getFunctions() {
        return this.functions;
    }

    public void setFunctions(List<IFunction> functions) {
        this.functions = functions;
    }

    @Override
    public List<IRole> getRoles() {
        return roles;
    }

    public void setRoles(List<IRole> roles) {
        this.roles = roles;
    }

}
