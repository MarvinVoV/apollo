package com.marvin.apollo.common.dal.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ArticleEntity implements Serializable {
    private long   id;
    private long   userId;
    private long   categoryId;
    private String title;
    private byte[] content;
    private int    top;
    private String tag;
    private int    invisible;
    private int    status;
    private Date   gmtCreate;
    private Date   gmtModified;
}
