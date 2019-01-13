package com.marvin.apollo.core.service.repository.impl;

import com.marvin.apollo.common.dal.entity.CategoryEntity;
import com.marvin.apollo.common.dal.mybatis.CategoryMapper;
import com.marvin.apollo.core.model.dto.CategoryDTO;
import com.marvin.apollo.core.service.repository.CategoryRepository;
import com.marvin.apollo.core.service.repository.convert.CategoryConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hufeng
 * @version CategoryRepositoryImpl.java, v 0.1 2019-01-13 23:58 Exp $
 */

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> queryAllByUserId(Long userId) {
        List<CategoryEntity> entities = categoryMapper.queryAllByUserId(userId);

        if (CollectionUtils.isEmpty(entities)) {
            return new ArrayList<>();
        }
        return CategoryConvert.convertList(entities);
    }
}
