package sun.focusblog.admin.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.focusblog.admin.domain.Comment;
import sun.focusblog.admin.services.CommentsService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by root on 2015/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/config/application-context.xml"})
public class CommentsServiceImplTest {

    @Autowired
    private CommentsService commentsService;

    @Test
    public void testList() throws Exception {
        String articleId = "48f408ba-a744-470b-86ce-e82177879e9d";

        List<Comment> list = commentsService.list(articleId, 2, 4);

        assertTrue(list.size() > 0);
    }
}