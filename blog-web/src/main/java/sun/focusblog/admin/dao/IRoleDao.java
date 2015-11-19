package sun.focusblog.admin.dao;

import sun.focusblog.admin.domain.auth.Role;

/**
 * Created by root on 2015/11/7.
 */
public interface IRoleDao {

    String NAMESPACE = "sun.focusblog.admin.domain.auth.Role";

    /**
     * Query user by unique userID
     */
    Role query(int roleId);
}
