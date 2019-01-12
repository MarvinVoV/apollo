package com.marvin.apollo.common.dal.mybatis;

import com.marvin.apollo.common.dal.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author hufeng
 * @version CategoryMapper.java, v 0.1 2019-01-12 20:36 Exp $
 */
@Repository
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryEntity, Long> {

}
