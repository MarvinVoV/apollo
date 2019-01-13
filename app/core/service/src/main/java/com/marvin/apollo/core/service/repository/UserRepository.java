package com.marvin.apollo.core.service.repository;

import com.marvin.apollo.core.model.dto.UserDTO;

/**
 * @author hufeng
 * @version UserRepository.java, v 0.1 2019-01-14 00:03 Exp $
 */

public interface UserRepository {
    UserDTO queryById(Long userId);
}
