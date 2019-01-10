package com.marvin.apollo.common.dal.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CategoryEntity {
    private long   id;
    private String name;
    private long   status;
    private Date   gmtCreate;
    private Date   gmtModified;
}
