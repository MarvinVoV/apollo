package sun.focusblog.admin.dao;

import sun.focusblog.admin.domain.auth.User;

/**
 * Created by root on 2015/11/7.
 */
public interface IUserDao {

    String NAMESPACE = "sun.focusblog.admin.domain.auth.User";

    /**
     * Query user by unique userID
     */
    User query(String userId);

    boolean persistUser(User user);

    boolean persistUserRole(String userId, int roleId);
}
