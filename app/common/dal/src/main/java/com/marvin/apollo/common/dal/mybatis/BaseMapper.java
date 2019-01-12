package com.marvin.apollo.common.dal.mybatis;

import java.io.Serializable;

/**
 * @author hufeng
 * @version BaseMapper.java, v 0.1 2019-01-12 19:54 Exp $
 */
public interface BaseMapper<T, PK extends Serializable> {

    int insert(T entity);

    T selectByPrimaryKey(PK id);

    int deleteByPrimaryKey(PK id);

    int updateByPrimaryKey(T entity);
}
