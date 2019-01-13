package com.marvin.apollo.core.model.dto;

import com.marvin.apollo.core.model.enums.RecordStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hufeng
 * @version CategoryDTO.java, v 0.1 2019-01-13 23:48 Exp $
 */

@Setter
@Getter
public class CategoryDTO implements Serializable {
    private Long         id;
    private Long         userId;
    private String       name;
    private RecordStatus recordStatus;
    private Date         createTime;
    private Date         modifiedTime;
}
