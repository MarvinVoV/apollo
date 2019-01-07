package com.apollo.dao.mapper;

import com.apollo.ApolloApplicationTests;
import com.apollo.dao.entity.CategoryEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hufeng
 * @version CategoryMapperTest.java, v 0.1 2019-01-08 02:11 Exp $
 */

public class CategoryMapperTest extends ApolloApplicationTests {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void testInsert() {
        CategoryEntity entity = new CategoryEntity();
        entity.setName("xxx");
        entity.setStatus(1);
        categoryMapper.insert(entity);
        System.out.println(entity.getId());
    }

}