package com.marvin.apollo.dal.mybatis;

import com.alibaba.fastjson.JSON;
import com.marvin.apollo.common.dal.entity.ArticleEntity;
import com.marvin.apollo.common.dal.entity.CategoryEntity;
import com.marvin.apollo.common.dal.mybatis.ArticleMapper;
import com.marvin.apollo.web.home.boot.BaseTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author hufeng
 * @version ArticleMapperTest.java, v 0.1 2019-01-12 20:23 Exp $
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleMapperTest extends BaseTest {
    @Autowired
    private ArticleMapper articleMapper;

    private static transient long ARTICLE_ID = -1;
    private static transient int  NOTE_ID    = 1;
    private static transient int  BOOK_ID    = 1;

    //    @Transactional
//    @Rollback
    @Test
    public void testA_insert() {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle("test2 title");
        entity.setContentOfMd("hello".getBytes());
        entity.setInvisible(1);
        entity.setTag("xxx");
        entity.setTop(1);
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(2);
        entity.setCategoryEntity(categoryEntity);
        entity.setRefBookId(BOOK_ID);
        entity.setRefNoteId(NOTE_ID);
        entity.setInvisible(1);
        int r = articleMapper.insert(entity);
        assertTrue(r > 0);
        ARTICLE_ID = entity.getId();
    }

    @Test
    public void testB_query() {
        ArticleEntity entity = articleMapper.selectByPrimaryKey(ARTICLE_ID);
        assertNotNull(entity);
        System.out.println(JSON.toJSONString(entity));
    }

    @Test
    public void testC_queryAll() {
        List<ArticleEntity> articleEntities = articleMapper.queryList(null);
        assertNotNull(articleEntities);
        System.out.println(JSON.toJSONString(articleEntities));
    }

    //    @Transactional
//    @Rollback
    @Test
    public void testD_update() {
        ArticleEntity entity = articleMapper.selectByPrimaryKey(ARTICLE_ID);
        assertNotNull(entity);

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);
        entity.setCategoryEntity(categoryEntity);
        entity.setTitle("my title");
        int r = articleMapper.updateByPrimaryKey(entity);
        assertEquals(1, r);

    }

    @Test
    public void testE_queryByRefIdentifier() {
        ArticleEntity entity = articleMapper.queryByRefIdentifier(NOTE_ID, BOOK_ID);
        assertNotNull(entity);
        System.out.println(JSON.toJSONString(entity));
    }

    @Test
    public void testDate() {
        Date modifiedTime = Date.from(Instant.parse("2019-01-26T14:42:55.809Z"));
        System.out.println(modifiedTime);

    }

}
