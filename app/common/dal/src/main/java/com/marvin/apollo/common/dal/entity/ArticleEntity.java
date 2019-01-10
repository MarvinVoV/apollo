package com.marvin.apollo.common.dal.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArticleEntity {
    private long   id;
    private long   userId;
    private long   categoryId;
    private String title;
    private byte[] content;
    private long   top;
    private String tag;
    private long   invisible;
    private long   status;
    private Date   gmtCreate;
    private Date   gmtModified;
}
