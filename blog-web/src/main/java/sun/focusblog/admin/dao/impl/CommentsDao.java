package sun.focusblog.admin.dao.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.focusblog.admin.dao.BaseDao;
import sun.focusblog.admin.dao.ICommentsDao;
import sun.focusblog.admin.domain.Comment;

import java.util.List;

/**
 * Created by root on 2015/12/16.
 * <p/>
 * Impl class
 */
@Repository
public class CommentsDao extends BaseDao implements ICommentsDao {
    @Override
    public List<Comment> list(String articleId, int start, int size) {
        return getSqlSession().selectList(buildStatement(NAMESPACE, "list"), articleId,
                new PageBounds(start, size));
    }

    @Transactional
    @Override
    public boolean delete(String id) {
        return getSqlSession().delete(buildStatement(NAMESPACE, "delete"), id) == 1;
    }

    @Override
    public Comment query(int id) {
        return getSqlSession().selectOne(buildStatement(NAMESPACE, "query"), id);
    }

    @Override
    public int count(String articleId) {
        return getSqlSession().selectOne(buildStatement(NAMESPACE, "count"), articleId);
    }
}
