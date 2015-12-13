package sun.focusblog.admin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.focusblog.admin.components.Helper;
import sun.focusblog.admin.dao.IArticleDao;
import sun.focusblog.admin.domain.Article;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.ArticleService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 2015/12/3.
 * <p/>
 * Article Service
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private IArticleDao articleDao;

    @Value("${personal.blog.pageSize:10}")
    private String pageSize;

    @Transactional
    @Override
    public boolean save(Article article) {
        return articleDao.save(article);
    }

    @Override
    public boolean saveWithInnerInfo(HttpSession httpSession, Article article) {
        if (article == null)
            return false;
        article.setUserId(Helper.getUser(httpSession).getUserId());
        article.setCreateDate(new Date());
        article.setUpdateDate(new Date());
        article.setOrder(0);
        return save(article);
    }

    @Transactional
    @Override
    public boolean update(Article article, User user) {
        if (article == null) {
            return false;
        }
        article.setUpdateDate(new Date());
        article.setUserId(user.getUserId());
        return articleDao.update(article) == 1;
    }

    @Override
    public Article query(String id) {
        return articleDao.query(id);
    }

    @Override
    public List<Article> listOrderByDate(User user, int start, int size) {
        return articleDao.listOrderByDate(user.getUserId(), start, size);
    }

    @Override
    public int countAll(User user) {
        return articleDao.countAll(user.getUserId());
    }

    @Override
    public String getPageSize() {
        return pageSize;
    }

}
