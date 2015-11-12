package sun.yamorn.blog.admin.dao.impl;

import org.springframework.stereotype.Repository;
import sun.yamorn.blog.admin.context.CacheNameSpace;
import sun.yamorn.blog.admin.dao.BaseDao;
import sun.yamorn.blog.admin.dao.IUserDao;
import sun.yamorn.blog.admin.domain.auth.User;
import sun.yamorn.blog.framework.cache.annotation.Cacheable;
import sun.yamorn.blog.framework.cache.util.ExpireConstants;

/**
 * Created by root on 2015/11/7.
 */
@Repository
public class UserDao extends BaseDao implements IUserDao {

    @Cacheable(namespace = CacheNameSpace.CACHE_USER, fieldsKey = {"#userId"}, expire = ExpireConstants.ONE_DAY)
    @Override
    public User query(String userId) {
        return getSqlSession().selectOne(buildStatement(NAMESPACE, "query"),
                getParamsBuilder()
                        .put("userId", userId)
                        .build()
        );
    }
}
