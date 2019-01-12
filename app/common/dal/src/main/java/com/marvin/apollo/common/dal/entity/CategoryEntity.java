package com.marvin.apollo.common.dal.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CategoryEntity implements Serializable {
    private long   id;
    private String name;
    private int    status;
    private Date   gmtCreate;
    private Date   gmtModified;
}
