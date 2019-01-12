package com.marvin.apollo.dal.mybatis;

import com.alibaba.fastjson.JSON;
import com.marvin.apollo.common.dal.entity.UserEntity;
import com.marvin.apollo.common.dal.mybatis.UserMapper;
import com.marvin.apollo.web.home.boot.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author hufeng
 * @version UserMapperTest.java, v 0.1 2019-01-12 19:22 Exp $
 */

public class UserMapperTest extends BaseTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInject() {
        assertNotNull(userMapper);
    }

    @Test
    public void testQueryById() {
        UserEntity entity = userMapper.selectByPrimaryKey(1L);
        assertNotNull(entity);
        assertEquals(1, entity.getId());
    }

    @Transactional
    @Rollback
    @Test
    public void testInsert() {
        UserEntity entity = new UserEntity();
        entity.setAccount("xxxyyy");
        entity.setAge(30);
        entity.setGender(1);
        entity.setStatus(2);
        userMapper.insert(entity);

        assertTrue(entity.getId() > 0);
    }

}
