package sun.focusblog.admin.dao.impl;

import org.springframework.stereotype.Repository;
import sun.focusblog.admin.dao.BaseDao;
import sun.focusblog.admin.dao.IArticleDao;
import sun.focusblog.admin.dao.ICategoryDao;
import sun.focusblog.admin.domain.Article;
import sun.focusblog.admin.domain.Category;

import java.util.List;

/**
 * Created by root on 2015/11/24.
 */
@Repository
public class ArticleDao extends BaseDao implements IArticleDao {
    @Override
    public boolean save(Article article) {
        return getSqlSession().insert(buildStatement(NAMESPACE, "save"), article) == 1;
    }
}
