package sun.focusblog.admin.dao.impl;

import org.springframework.stereotype.Repository;
import sun.focusblog.admin.context.CacheNameSpace;
import sun.focusblog.admin.dao.BaseDao;
import sun.focusblog.admin.dao.IRoleDao;
import sun.focusblog.admin.domain.auth.Role;
import sun.focusblog.framework.cache.annotation.Cacheable;
import sun.focusblog.framework.cache.util.ExpireConstants;

/**
 * Created by root on 2015/11/19.
 */
@Repository
public class RoleDao extends BaseDao implements IRoleDao {

    @Cacheable(namespace = CacheNameSpace.CACHE_ROLE, fieldsKey = {"#roleId"}, expire = ExpireConstants.ONE_DAY)
    @Override
    public Role query(int roleId) {
        return getSqlSession().selectOne(buildStatement(NAMESPACE, "queryRoleById"),
                getParamsBuilder()
                .put("roleId", roleId)
                .build()
        );
    }
}
