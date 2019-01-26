package com.marvin.apollo.common.dal.mybatis;

import com.marvin.apollo.common.dal.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hufeng
 * @version ArticleMapper.java, v 0.1 2019-01-12 20:17 Exp $
 */
@Repository
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity, Long> {
    List<ArticleEntity> queryList(@Param("categoryId") Long categoryId);

    ArticleEntity queryByRefIdentifier(@Param("refNoteId") Integer refNoteId, @Param("refBookId") Integer refBookId);
}
