package com.marvin.apollo.core.service.repository.convert;

import com.marvin.apollo.common.dal.entity.UserEntity;
import com.marvin.apollo.core.model.domain.User;
import com.marvin.apollo.core.model.dto.UserDTO;
import com.marvin.apollo.core.model.enums.Gender;
import com.marvin.apollo.core.model.enums.RecordStatus;

/**
 * @author hufeng
 * @version UserConvert.java, v 0.1 2019-01-13 23:54 Exp $
 */

public class UserConvert {
    public static UserDTO convert(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setAccount(entity.getAccount());
        dto.setAge(entity.getAge());
        dto.setGender(Gender.getByCode(entity.getGender()));
        dto.setCreateTime(entity.getGmtCreate());
        dto.setModifiedTime(entity.getGmtModified());
        dto.setRecordStatus(RecordStatus.getByCode(entity.getStatus()));
        return dto;
    }

    public static User convert(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setAccount(dto.getAccount());
        user.setGender(dto.getGender());
        return user;
    }
}
