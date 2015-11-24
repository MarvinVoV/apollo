package sun.focusblog.admin.dao.impl;

import org.springframework.stereotype.Repository;
import sun.focusblog.admin.dao.BaseDao;
import sun.focusblog.admin.dao.ICategoryDao;
import sun.focusblog.admin.domain.Category;

/**
 * Created by root on 2015/11/24.
 */
@Repository
public class CategoryDao extends BaseDao implements ICategoryDao {
    @Override
    public boolean save(Category category) {
        return getSqlSession().insert(buildStatement(NAMESPACE, "saveCategory"), category) == 1;
    }
}
