package com.apollo.dao.mapper;

import com.apollo.dao.entity.ArticleEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

/**
 * @author hufeng
 * @version ArticleMapper.java, v 0.1 2019-01-08 02:06 Exp $
 */

@Mapper
@Repository
public interface ArticleMapper {
    @Insert("insert into article(id,user_id,category_id,title,content,top,tag,invisible,status,gmt_create,gmt_modified) values(#{id},#{userId},#{categoryId},#{title},#{content},#{top},#{tag},#{invisible},#{status},now(),now())")
    @SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", keyColumn = "id", before = false, resultType = int.class)
    int insert(ArticleEntity articleEntity);


    @Select("select id,user_id,category_id,title,content,top,tag,invisible,status,gmt_create,gmt_modified from article where id=#{id}")
    ArticleEntity queryById(long id);
}
