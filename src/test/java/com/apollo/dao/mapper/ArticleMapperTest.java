package com.apollo.dao.mapper;

import com.apollo.ApolloApplicationTests;
import com.apollo.dao.entity.ArticleEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * @author hufeng
 * @version ArticleMapperTest.java, v 0.1 2019-01-08 02:11 Exp $
 */

public class ArticleMapperTest extends ApolloApplicationTests {
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void testInsert() {
        ArticleEntity entity = new ArticleEntity();
        entity.setCategoryId(1);
        entity.setContent("xxxx".getBytes());
        entity.setTitle("xxxx");
        entity.setStatus(1);
        entity.setTag("xx");
        entity.setTop(1);
        entity.setUserId(1);
        articleMapper.insert(entity);
        System.out.println(entity.getId());
    }

    @Test
    public void testQueryById() {
        ArticleEntity entity = articleMapper.queryById(1);
        assertNotNull(entity);
    }
}