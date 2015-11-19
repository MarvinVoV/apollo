package sun.focusblog.admin.dao.impl;

import org.springframework.stereotype.Repository;
import sun.focusblog.admin.context.CacheNameSpace;
import sun.focusblog.admin.dao.BaseDao;
import sun.focusblog.admin.dao.IFunctionDao;
import sun.focusblog.admin.domain.auth.Function;
import sun.focusblog.framework.cache.annotation.Cacheable;
import sun.focusblog.framework.cache.util.ExpireConstants;

/**
 * Created by root on 2015/11/19.
 */
@Repository
public class FunctionDao extends BaseDao implements IFunctionDao {

    @Cacheable(namespace = CacheNameSpace.CACHE_FUNCTION, fieldsKey = {"#id"}, expire = ExpireConstants.ONE_DAY)
    @Override
    public Function queryFunctionById(int id) {
        return getSqlSession().selectOne(buildStatement(NAMESPACE, "queryFunctionById"),
                getParamsBuilder()
                        .put("functionId", id)
                        .build()
        );
    }
}
