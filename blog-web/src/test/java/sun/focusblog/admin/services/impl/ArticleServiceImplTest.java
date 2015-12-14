package sun.focusblog.admin.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import sun.focusblog.admin.domain.Article;
import sun.focusblog.admin.services.ArticleService;

import static org.junit.Assert.*;

/**
 * Created by root on 2015/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-context.xml"})
public class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @Transactional
    @Rollback
    @Test
    public void testSave() throws Exception {
        Article article = new Article();
        article.setUserId("tom");
        articleService.save(article);
    }
}