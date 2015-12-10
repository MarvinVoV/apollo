package sun.focusblog.admin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.focusblog.admin.components.Helper;
import sun.focusblog.admin.dao.IArticleDao;
import sun.focusblog.admin.domain.Article;
import sun.focusblog.admin.services.ArticleService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by root on 2015/12/3.
 *
 * Article Service
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private IArticleDao articleDao;

    @Transactional
    @Override
    public boolean save(Article article) {
        return articleDao.save(article);
    }

    @Override
    public boolean saveWithInnerInfo(HttpSession httpSession, Article article) {
        if(article == null)
            return false;
        article.setUserId(Helper.getUser(httpSession).getUserId());
        article.setCreateDate(new Date());
        article.setUpdateDate(new Date());
        article.setOrder(0);
        return save(article);
    }

    @Override
    public Article query(String id) {
        return articleDao.query(id);
    }

}
