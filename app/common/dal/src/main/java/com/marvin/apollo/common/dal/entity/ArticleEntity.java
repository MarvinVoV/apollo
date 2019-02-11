package com.marvin.apollo.common.dal.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ArticleEntity implements Serializable {
    private long           id;
    private int            refNoteId;
    private int            refBookId;
    private String         title;
    private byte[]         contentOfMd;
    private byte[]         contentOfHtml;
    private int            top;
    private String         tag;
    private int            invisible;
    private Integer        status;
    private int            viewCount;
    private int            likeCount;
    private CategoryEntity categoryEntity;
    private Date           gmtCreate;
    private Date           gmtModified;
}
