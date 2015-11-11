package sun.yamorn.blog.admin.cache.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.yamorn.blog.framework.cache.demo.IHello;

import static org.junit.Assert.*;

/**
 * Created by root on 2015/11/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-context.xml"})
public class IHelloTest {
    @Autowired
    private IHello hello;
    @Test
    public void testQuery() throws Exception {
        String str = hello.query("Tom", "admin");
        assertNotNull(str);
    }
}