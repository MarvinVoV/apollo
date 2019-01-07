package com.apollo.dao.mapper;

import com.apollo.dao.entity.CategoryEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

/**
 * @author hufeng
 * @version CategoryMapper.java, v 0.1 2019-01-08 02:06 Exp $
 */

@Mapper
@Repository
public interface CategoryMapper {
    @Insert("insert into category(id, name, status, gmt_create, gmt_modified) values(#{id},#{name},#{status},now(),now())")
    @SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", keyColumn = "id", before = false, resultType = int.class)
    int insert(CategoryEntity categoryEntity);
}
