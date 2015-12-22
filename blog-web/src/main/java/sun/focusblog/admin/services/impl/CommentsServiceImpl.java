package sun.focusblog.admin.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.focusblog.admin.dao.ICommentsDao;
import sun.focusblog.admin.domain.Comment;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.CommentsService;

import java.util.*;

/**
 * Created by root on 2015/12/16.
 * <p/>
 * Article comments service
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private ICommentsDao commentsDao;

    @Value("${personal.blog.comment.index:1}")
    private String pageIndex;

    @Value("${personal.blog.comment.pageSize:10}")
    private String pageSize;

    @Override
    public List<Comment> defaultList(String articleId) {
        return list(articleId, Integer.valueOf(pageIndex), Integer.valueOf(pageSize));
    }

    @Override
    public List<Comment> list(String articleId, int start, int size) {
        return commentsDao.list(articleId, start, size);
    }


    @Override
    public Comment query(String id) {
        return commentsDao.query(id);
    }

    @Override
    public int count(String articleId) {
        return commentsDao.count(articleId);
    }

    @Override
    public int getPageSize() {
        return Integer.valueOf(this.pageSize);
    }

    @Override
    public boolean save(Comment comment, User user) {
        if (comment != null && user != null) {
            if (StringUtils.isEmpty(comment.getId())) {
                comment.setId(UUID.randomUUID().toString());
            }
            comment.setUser(user);
            comment.setDate(new Date());
            return commentsDao.save(comment);
        }
        return false;
    }

    @Override
    public boolean update(Comment comment) {
        return commentsDao.update(comment);
    }
}
