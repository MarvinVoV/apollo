package com.marvin.apollo.core.service.repository.convert;

import com.marvin.apollo.common.dal.entity.ArticleEntity;
import com.marvin.apollo.core.model.dto.ArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author hufeng
 * @version ArticleConvert.java, v 0.1 2019-01-13 21:11 Exp $
 */
@Mapper
public interface ArticleConvert extends DefaultConvert {
    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    @Mappings({
            @Mapping(source = "invisible", target = "invisibleStatus"),
            @Mapping(source = "status", target = "recordStatus"),
            @Mapping(source = "top", target = "top"),
            @Mapping(source = "gmtCreate", target = "createTime"),
            @Mapping(source = "gmtModified", target = "modifiedTime"),
    })
    ArticleDto entityToDto(ArticleEntity entity);

    List<ArticleDto> entitiesToDtos(List<ArticleEntity> entities);
}
