package sun.yamorn.blog.admin.dao.impl;

import org.springframework.stereotype.Repository;
import sun.yamorn.blog.admin.dao.BaseDao;
import sun.yamorn.blog.admin.dao.IUserDao;
import sun.yamorn.blog.admin.domain.auth.IUser;

/**
 * Created by root on 2015/11/7.
 */
@Repository
public class UserDao extends BaseDao implements IUserDao {

    @Override
    public IUser query(String userId) {
        return getSqlSession().selectOne(buildStatement(NAMESPACE, "query"),
                getParamsBuilder()
                        .put("userId", userId)
                        .build()
        );
    }
}
