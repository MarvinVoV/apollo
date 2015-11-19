package sun.focusblog.admin.services.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import sun.focusblog.admin.dao.IUserDao;
import sun.focusblog.admin.domain.auth.Role;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.UserService;
import sun.focusblog.framework.cache.redis.StringCacheStorage;

/**
 * Created by root on 2015/11/19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StringCacheStorage cacheStorage;

    // default 24 hours
    @Value("${email.verify.expire:86400}")
    private String emailVerifyExpire;

    @Autowired
    private IUserDao userDao;

    @Override
    public String cacheEmailVerifyKey(User user) {
        String randomString = RandomStringUtils.random(10, true, true);
        String validKey = Base64Utils.encodeToString(user.getUserName().getBytes()) + randomString;
        cacheStorage.setEx(validKey, user, Integer.valueOf(this.emailVerifyExpire));
        return validKey;
    }

    @Override
    public User getVerifyUser(String verifyKey) {
        return cacheStorage.get(verifyKey, User.class);
    }

    @Override
    public void saveUser(User user) {
        userDao.persistUser(user);

        for (Role role : user.getRoles()) {
            userDao.persistUserRole(user.getUserId(), role.getRoleId());
        }
    }

    @Override
    public User query(String userId) {
        return userDao.query(userId);
    }
}
