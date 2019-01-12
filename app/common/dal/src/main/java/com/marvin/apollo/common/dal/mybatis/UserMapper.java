package com.marvin.apollo.common.dal.mybatis;

import com.marvin.apollo.common.dal.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author hufeng
 * @version UserMapper.java, v 0.1 2019-01-12 20:00 Exp $
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<UserEntity, Long> {

}
