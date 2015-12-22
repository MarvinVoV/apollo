package sun.focusblog.framework.cache.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sun.focusblog.admin.domain.Comment;
import sun.focusblog.admin.domain.auth.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by root on 2015/12/22.
 */
@RunWith(JUnit4.class)
public class JsonSerializeUtilsTest {

    @Test
    public void testToObject() throws Exception {
        List<Comment> list = new LinkedList<>();
        Comment a = new Comment();
        a.setId("a");
        a.setArticleId("a-article");
        a.setContent("a-hello");
        a.setDate(new Date());
        User user = new User();
        user.setUserId("user-a");
        user.setUserName("user-a-name");
        a.setUser(user);

        Comment b = new Comment();
        b.setId("b");
        b.setArticleId("b-article");
        b.setContent("b-hello");
        b.setDate(new Date());

        list.add(a);
        list.add(b);

        String json = JsonSerializeUtils.toJsonString(list);
        assertNotNull(json);
        System.out.println(json);
    }

    @Test
    public void toList() throws Exception {
        String json = "[{\"id\":\"a\",\"articleId\":\"a-article\",\"content\":\"a-hello\",\"date\":\"2015-12-22 15:22:40\",\"user\":{\"userId\":\"user-a\",\"userName\":\"user-a-name\",\"status\":0,\"functions\":[],\"roles\":[]}},{\"id\":\"b\",\"articleId\":\"b-article\",\"content\":\"b-hello\",\"date\":\"2015-12-22 15:22:40\"}]";

        List<Comment> list = JsonSerializeUtils.toList(json, Comment.class);
        assertTrue(list != null && list.size() > 0);
    }
}