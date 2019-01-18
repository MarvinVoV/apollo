package com.marvin.apollo.dal.mybatis;

import com.marvin.apollo.web.home.boot.BaseTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author hufeng
 * @version MapperIntegrationTests.java, v 0.1 2019-01-12 20:46 Exp $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArticleMapperTest.class,
        CategoryMapperTest.class
})
public class MapperIntegrationTests extends BaseTest {
}
