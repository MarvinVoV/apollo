package com.marvin.apollo.web.home.boot;

import com.marvin.apollo.web.boot.ApolloApplication;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hufeng
 * @version BaseTest.java, v 0.1 2019-01-12 19:21 Exp $
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApolloApplication.class})
public class BaseTest {
    @Ignore
    @Test
    public void contextLoads() {
    }
}
