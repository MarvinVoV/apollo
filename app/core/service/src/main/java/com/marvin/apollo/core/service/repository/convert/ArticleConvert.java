package com.marvin.apollo.core.service.repository.convert;

import com.marvin.apollo.common.dal.entity.ArticleEntity;
import com.marvin.apollo.core.model.dto.ArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
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
            @Mapping(source = "gmtCreate", target = "createTime"),
            @Mapping(source = "gmtModified", target = "modifiedTime"),
            @Mapping(source = "categoryEntity.id", target = "categoryId"),
            @Mapping(source = "categoryEntity.name", target = "categoryName"),
    })
    ArticleDto entityToDto(ArticleEntity entity);

    List<ArticleDto> entitiesToDtos(List<ArticleEntity> entities);

    @Mappings({
            @Mapping(target = "invisible", source = "invisibleStatus"),
            @Mapping(target = "gmtCreate", source = "createTime"),
            @Mapping(target = "gmtModified", source = "modifiedTime"),
            @Mapping(target = "categoryEntity.id", source = "categoryId"),
            @Mapping(target = "categoryEntity.name", source = "categoryName"),
    })
    ArticleEntity dtoToEntity(ArticleDto dto);

    default String bytesToString(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return new String(data, Charset.forName("UTF-8"));
    }

    default byte[] stringToBytes(String data) {
        if (StringUtils.isEmpty(data)) {
            return new byte[0];
        }
        return data.getBytes(Charset.forName("UTF-8"));
    }
}
