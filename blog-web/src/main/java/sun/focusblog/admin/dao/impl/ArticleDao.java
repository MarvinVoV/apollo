package sun.focusblog.admin.dao.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.stereotype.Repository;
import sun.focusblog.admin.annotation.Cipher;
import sun.focusblog.admin.annotation.CipherType;
import sun.focusblog.admin.dao.BaseDao;
import sun.focusblog.admin.dao.IArticleDao;
import sun.focusblog.admin.dao.ICategoryDao;
import sun.focusblog.admin.domain.Article;
import sun.focusblog.admin.domain.Category;

import java.util.List;

/**
 * Created by root on 2015/11/24.
 * <p/>
 * Implements class
 */
@Repository
public class ArticleDao extends BaseDao implements IArticleDao {

    @Cipher(arguments = {"article.userId"}, cipherType = CipherType.ENCRYPT)
    @Override
    public boolean save(Article article) {
        return getSqlSession().insert(buildStatement(NAMESPACE, "save"), article) == 1;
    }

    @Override
    public Article query(String id) {
        return getSqlSession().selectOne(buildStatement(NAMESPACE, "query"), id);
    }

    @Override
    public List<Article> listOrderByDate(String userId, int start, int size) {
        return getSqlSession().selectList(buildStatement(NAMESPACE, "listOrderByDate"), userId,
                new PageBounds(start, size));
    }

    @Override
    public int countAll(String userId) {
        return getSqlSession().selectOne(buildStatement(NAMESPACE, "countAll"), userId);
    }

    @Override
    public int update(Article article) {
        return getSqlSession().update(buildStatement(NAMESPACE, "update"), article);
    }
}
