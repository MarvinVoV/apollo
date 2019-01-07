package com.apollo.dao.mapper;

import com.apollo.ApolloApplicationTests;
import com.apollo.dao.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hufeng
 * @version UserMapperTest.java, v 0.1 2019-01-08 01:01 Exp $
 */
public class UserMapperTest extends ApolloApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Transactional()
    @Rollback
    @Test
    public void testInsert() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAccount("admin");
        userEntity.setAge(20);
        userEntity.setStatus(1);
        userEntity.setGender(1);
        userMapper.insert(userEntity);
        System.out.println(userEntity.getId());
    }
}