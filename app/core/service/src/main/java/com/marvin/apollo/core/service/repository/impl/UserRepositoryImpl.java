package com.marvin.apollo.core.service.repository.impl;

import com.marvin.apollo.common.dal.entity.UserEntity;
import com.marvin.apollo.common.dal.mybatis.UserMapper;
import com.marvin.apollo.core.model.dto.UserDto;
import com.marvin.apollo.core.service.repository.UserRepository;
import com.marvin.apollo.core.service.repository.convert.UserConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hufeng
 * @version UserRepositoryImpl.java, v 0.1 2019-01-14 00:04 Exp $
 */

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto queryById(Long userId) {
        UserEntity entity = userMapper.selectByPrimaryKey(userId);
        return UserConvert.INSTANCE.entityToDto(entity);
    }
}
