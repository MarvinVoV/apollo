package sun.focusblog.admin.services;

import sun.focusblog.admin.domain.auth.User;

/**
 * Created by root on 2015/11/19.
 *
 * Provide user related methods
 */
public interface UserService {

    /**
     * The verify key which be consist of username encoded by BASE64 and ten random characters.
     * The verify value which be consist of password and email.
     * verify string cached default 24 hours.
     * @param user  User entity
     * @return      verify key string
     */
    String cacheEmailVerifyKey(User user);

    /**
     * Get user from cache by verify key.
     * @param verifyKey verify key
     * @return          User entity
     */
    User getVerifyUser(String verifyKey);

    /**
     * Persist user and user's role
     * @param user user class
     */
    void saveUser(User user);

    /**
     * Query user by userId
     */
    User query(String userId);
}
