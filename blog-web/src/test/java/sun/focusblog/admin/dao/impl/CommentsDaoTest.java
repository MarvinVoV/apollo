package sun.focusblog.admin.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.focusblog.admin.dao.ICommentsDao;
import sun.focusblog.admin.domain.Comment;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by root on 2015/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/config/application-context.xml"})
public class CommentsDaoTest {
    @Autowired
    private ICommentsDao commentsDao;

    @Test
    public void testList() throws Exception {
        String articleId = "48f408ba-a744-470b-86ce-e82177879e9d";
        List<Comment> list = commentsDao.list(articleId, 2, 22);
        assertNotNull(list);
    }
}