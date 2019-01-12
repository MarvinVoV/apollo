package com.marvin.apollo.common.dal.mybatis;

import com.marvin.apollo.common.dal.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author hufeng
 * @version ArticleMapper.java, v 0.1 2019-01-12 20:17 Exp $
 */
@Repository
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity, Long> {

}
