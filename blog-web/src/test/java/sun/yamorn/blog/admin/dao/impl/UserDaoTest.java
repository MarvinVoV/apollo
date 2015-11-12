package sun.yamorn.blog.admin.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sun.yamorn.blog.admin.dao.IUserDao;
import sun.yamorn.blog.admin.domain.auth.User;

import static org.junit.Assert.assertNotNull;

/**
 * Created by root on 2015/11/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/config/application-context.xml"})
public class UserDaoTest {
    @Autowired
    private IUserDao userDao;

    //    @Ignore
    @Test
    @Transactional
    public void testQuery() throws Exception {
        User user = userDao.query("yamorn");
        assertNotNull(user);
    }
}