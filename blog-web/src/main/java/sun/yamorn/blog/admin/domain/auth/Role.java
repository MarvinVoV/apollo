package sun.yamorn.blog.admin.domain.auth;

/**
 * Created by root on 2015/11/7.
 *
 * User role entity.
 */
public class Role{
    private int roleId;

    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
