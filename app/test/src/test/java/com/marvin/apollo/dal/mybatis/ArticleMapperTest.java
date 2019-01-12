package com.marvin.apollo.dal.mybatis;

import com.alibaba.fastjson.JSON;
import com.marvin.apollo.common.dal.entity.ArticleEntity;
import com.marvin.apollo.common.dal.mybatis.ArticleMapper;
import com.marvin.apollo.web.home.boot.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author hufeng
 * @version ArticleMapperTest.java, v 0.1 2019-01-12 20:23 Exp $
 */

public class ArticleMapperTest extends BaseTest {
    @Autowired
    private ArticleMapper articleMapper;

    @Transactional
    @Rollback
    @Test
    public void testInsert() {
        ArticleEntity entity = new ArticleEntity();
        entity.setUserId(1);
        entity.setCategoryId(1);
        entity.setTitle("test title");
        entity.setContent("hello".getBytes());
        entity.setInvisible(1);
        entity.setTag("xxx");
        entity.setTop(1);
        int r = articleMapper.insert(entity);
        assertTrue(r > 0);
    }

    @Test
    public void testQuery() {
        ArticleEntity entity = articleMapper.selectByPrimaryKey(1L);
        assertNotNull(entity);
        System.out.println(JSON.toJSONString(entity));
    }
}
