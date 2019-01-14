package com.marvin.apollo.core.service.repository.convert;

import com.marvin.apollo.common.dal.entity.CategoryEntity;
import com.marvin.apollo.core.model.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author hufeng
 * @version CategoryConvert.java, v 0.1 2019-01-13 23:55 Exp $
 */

@Mapper
public interface CategoryConvert extends DefaultConvert {
    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    @Mappings({
            @Mapping(source = "status", target = "recordStatus"),
            @Mapping(source = "gmtCreate", target = "createTime"),
            @Mapping(source = "gmtModified", target = "modifiedTime")
    })
    CategoryDto entityToDto(CategoryEntity entity);

    List<CategoryDto> entitiesToDtos(List<CategoryEntity> entities);

}
