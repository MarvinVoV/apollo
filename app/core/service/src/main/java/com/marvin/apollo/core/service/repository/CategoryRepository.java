package com.marvin.apollo.core.service.repository;

import com.marvin.apollo.core.model.dto.CategoryDto;

import java.util.List;

/**
 * @author hufeng
 * @version CategoryRepository.java, v 0.1 2019-01-13 23:57 Exp $
 */

public interface CategoryRepository {
    List<CategoryDto> queryAll();
}
