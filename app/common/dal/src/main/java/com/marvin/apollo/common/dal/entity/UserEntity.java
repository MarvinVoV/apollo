package com.marvin.apollo.common.dal.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hufeng
 * @version UserEntity.java, v 0.1 2019-01-08 00:01 Exp $
 */
@Setter
@Getter
public class UserEntity implements Serializable {
    private long    id;
    private String account;
    private int    age;
    private int    gender;
    private int    status;
    private Date   gmtCreate;
    private Date   gmtModified;
}
