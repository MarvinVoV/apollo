package com.marvin.apollo.core.model.dto;

import com.marvin.apollo.core.model.enums.Gender;
import com.marvin.apollo.core.model.enums.RecordStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hufeng
 * @version UserDTO.java, v 0.1 2019-01-13 23:50 Exp $
 */
@Setter
@Getter
public class UserDTO implements Serializable {
    private Long         id;
    private String       account;
    private int          age;
    private Gender       gender;
    private RecordStatus recordStatus;
    private Date         createTime;
    private Date         modifiedTime;
}
