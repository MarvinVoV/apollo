package com.marvin.apollo.core.service.repository.convert;

import com.marvin.apollo.common.dal.entity.UserEntity;
import com.marvin.apollo.core.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author hufeng
 * @version UserConvert.java, v 0.1 2019-01-13 23:54 Exp $
 */
@Mapper
public interface UserConvert extends DefaultConvert{
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({
            @Mapping(source = "status", target = "recordStatus"),
            @Mapping(source = "gender", target = "gender"),
            @Mapping(source = "gmtCreate", target = "createTime"),
            @Mapping(source = "gmtModified", target = "modifiedTime")
    })
    UserDto entityToDto(UserEntity entity);

    List<UserDto> entitiesToDtos(List<UserEntity> entities);
}
