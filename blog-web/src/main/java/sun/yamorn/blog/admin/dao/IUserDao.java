package sun.yamorn.blog.admin.dao;

import sun.yamorn.blog.admin.domain.auth.User;

/**
 * Created by root on 2015/11/7.
 */
public interface IUserDao {

    String NAMESPACE = "sun.yamorn.blog.admin.domain.auth.User";

    /**
     * Query user by unique userID
     */
    User query(String userId);
}
