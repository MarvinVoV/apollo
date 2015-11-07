package sun.yamorn.blog.admin.dao;

import sun.yamorn.blog.admin.domain.auth.IUser;

/**
 * Created by root on 2015/11/7.
 */
public interface IUserDao {

    String NAMESPACE = "sun.yamorn.blog.admin.domain.auth.User";

    /**
     * Query user by unique userID
     */
    IUser query(String userId);
}
