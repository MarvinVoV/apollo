package com.marvin.apollo.dal.mybatis;

import com.alibaba.fastjson.JSON;
import com.marvin.apollo.common.dal.entity.ArticleEntity;
import com.marvin.apollo.common.dal.entity.CategoryEntity;
import com.marvin.apollo.common.dal.mybatis.ArticleMapper;
import com.marvin.apollo.web.home.boot.BaseTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

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
        entity.setTitle("test2 title");
        entity.setContent("hello".getBytes());
        entity.setInvisible(1);
        entity.setTag("xxx");
        entity.setTop(1);
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(2);
        entity.setCategoryEntity(categoryEntity);
        int r = articleMapper.insert(entity);
        assertTrue(r > 0);
    }

    @Test
    public void testQuery() {
        ArticleEntity entity = articleMapper.selectByPrimaryKey(1L);
        assertNotNull(entity);
        System.out.println(JSON.toJSONString(entity));
    }

    @Test
    public void testQueryAll() {
        List<ArticleEntity> articleEntities =  articleMapper.queryList(null);
        assertNotNull(articleEntities);
        System.out.println(JSON.toJSONString(articleEntities));
    }

    @Ignore
    @Test
    public void testUpdate() {
        ArticleEntity entity = articleMapper.selectByPrimaryKey(4L);
        assertNotNull(entity);


        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);
        entity.setCategoryEntity(categoryEntity);
        entity.setTitle("my title");
        int r = articleMapper.updateByPrimaryKey(entity);
        assertEquals(1, r);

    }
}
