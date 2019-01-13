package com.marvin.apollo.core.service.repository.convert;

import com.marvin.apollo.common.dal.entity.CategoryEntity;
import com.marvin.apollo.core.model.domain.Category;
import com.marvin.apollo.core.model.dto.CategoryDTO;
import com.marvin.apollo.core.model.enums.RecordStatus;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hufeng
 * @version CategoryConvert.java, v 0.1 2019-01-13 23:55 Exp $
 */

public class CategoryConvert {
    public static CategoryDTO convert(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRecordStatus(RecordStatus.getByCode(entity.getStatus()));
        dto.setUserId(entity.getUserId());
        dto.setModifiedTime(entity.getGmtModified());
        dto.setCreateTime(entity.getGmtCreate());
        return dto;
    }

    public static List<CategoryDTO> convertList(List<CategoryEntity> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return new ArrayList<>();
        }
        List<CategoryDTO> dtoList = new ArrayList<>();
        entityList.forEach(item -> dtoList.add(convert(item)));
        return dtoList;
    }

    public static Category convert(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }
}
