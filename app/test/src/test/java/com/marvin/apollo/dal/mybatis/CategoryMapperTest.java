package com.marvin.apollo.dal.mybatis;

import com.marvin.apollo.common.dal.entity.CategoryEntity;
import com.marvin.apollo.common.dal.mybatis.CategoryMapper;
import com.marvin.apollo.web.home.boot.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author hufeng
 * @version CategoryMapperTest.java, v 0.1 2019-01-12 20:40 Exp $
 */

public class CategoryMapperTest extends BaseTest {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void testQuery() {
        CategoryEntity entity = categoryMapper.selectByPrimaryKey(1L);
        assertNotNull(entity);
        assertEquals(1, entity.getId());
    }

    @Transactional
    @Rollback()
    @Test
    public void testInsert() {
        CategoryEntity entity = new CategoryEntity();
        entity.setName("tech1");
        entity.setStatus(1);
        int r = categoryMapper.insert(entity);
        assertTrue(r > 0);
    }
}

