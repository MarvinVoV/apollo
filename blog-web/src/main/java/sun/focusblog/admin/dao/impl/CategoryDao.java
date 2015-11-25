package sun.focusblog.admin.dao.impl;

import org.springframework.stereotype.Repository;
import sun.focusblog.admin.dao.BaseDao;
import sun.focusblog.admin.dao.ICategoryDao;
import sun.focusblog.admin.domain.Category;

import java.util.List;

/**
 * Created by root on 2015/11/24.
 */
@Repository
public class CategoryDao extends BaseDao implements ICategoryDao {
    @Override
    public boolean save(Category category) {
        return getSqlSession().insert(buildStatement(NAMESPACE, "save"), category) == 1;
    }

    @Override
    public List<Category> query(String userId) {
        return getSqlSession().selectList(buildStatement(NAMESPACE, "query"), userId);
    }

    @Override
    public boolean update(Category category) {
        return getSqlSession().update(buildStatement(NAMESPACE, "update"), category) == 1;
    }

    @Override
    public boolean delete(Category category) {
        return getSqlSession().delete(buildStatement(NAMESPACE, "delete"), category.getId()) == 1;
    }
}
