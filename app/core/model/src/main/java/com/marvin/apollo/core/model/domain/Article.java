package com.marvin.apollo.core.model.domain;

import com.marvin.apollo.core.model.enums.InvisibleStatus;
import com.marvin.apollo.core.model.enums.RecordStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hufeng
 * @version Article.java, v 0.1 2019-01-13 23:26 Exp $
 */
@Setter
@Getter
public class Article implements Serializable {
    private User            user;
    private Category        category;
    private String          title;
    private byte[]          content;
    private boolean         top;
    private String          tag;
    private InvisibleStatus invisibleStatus;
    private RecordStatus    recordStatus;
    private Date            createTime;
    private Date            modifiedTime;
}
