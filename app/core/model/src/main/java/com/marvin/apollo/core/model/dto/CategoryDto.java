package com.marvin.apollo.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hufeng
 * @version CategoryDto.java, v 0.1 2019-01-13 23:48 Exp $
 */

@Setter
@Getter
public class CategoryDto implements Serializable {
    private Long         id;
    private String       name;
    private Date         createTime;
    private Date         modifiedTime;
}
