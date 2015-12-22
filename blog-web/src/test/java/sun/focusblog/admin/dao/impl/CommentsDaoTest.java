package sun.focusblog.admin.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.focusblog.admin.dao.ICommentsDao;
import sun.focusblog.admin.domain.Comment;
import sun.focusblog.admin.domain.auth.User;

import java.util.ArrayList;
import java.util.Date;
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

    @Test
    public void testSave() throws Exception{
        Comment comment = new Comment();
        comment.setId("a");
        comment.setDate(new Date());
        comment.setContent("a-hello");
        User user = new User();
        user.setUserId("a-user");
        user.setUserName("a-user-name");
        comment.setUser(user);

        Comment parent = new Comment();
        parent.setId("p");

        comment.setParent(comment);


        Comment update = new Comment();
        update.setId("a");
        List<Comment> list= new ArrayList<>();
        list.add(comment);
        update.setComments(list);



        boolean result = commentsDao.update(update);
        assertTrue(result);
    }

    @Test
    public void testQuery() throws Exception{
        Comment comment = commentsDao.query("a");
        assertNotNull(comment);
    }
}