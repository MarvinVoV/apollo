package sun.focusblog.admin.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.focusblog.admin.context.UserRole;
import sun.focusblog.admin.domain.auth.Role;

import static org.junit.Assert.*;

/**
 * Created by root on 2015/11/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-context.xml"})
public class IRoleDaoTest {

    @Autowired
    private IRoleDao roleDao;

    @Test
    public void testQuery() throws Exception {
        Role role = roleDao.query(UserRole.ROLE_USER.getValue());
        assertNotNull(role);
    }
}