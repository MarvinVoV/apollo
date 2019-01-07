package com.apollo.dao.mapper;

import com.apollo.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

/**
 * @author hufeng
 * @version UserMapper.java, v 0.1 2019-01-08 00:07 Exp $
 */
@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user(id, account, age, gender, status, gmt_create, gmt_modified) values(#{id},#{account},#{age},#{gender},#{status},now(),now())")
    @SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", keyColumn = "id", before = false, resultType = int.class)
    int insert(UserEntity userEntity);
}
