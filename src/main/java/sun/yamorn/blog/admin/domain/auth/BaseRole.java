package sun.yamorn.blog.admin.domain.auth;

/**
 * Created by root on 2015/11/7.
 */
public class BaseRole implements IRole {
    protected int roleId;

    protected String roleName;

    @Override
    public int getRoleId() {
        return this.roleId;
    }

    @Override
    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
