package com.marvin.apollo.core.model.dto;

import com.marvin.apollo.core.model.enums.InvisibleStatus;
import com.marvin.apollo.core.model.enums.RecordStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hufeng
 * @version ArticleDTO.java, v 0.1 2019-01-13 20:56 Exp $
 */
@Setter
@Getter
@ToString
public class ArticleDTO implements Serializable {
    private Long            id;
    private Long            userId;
    private Long            categoryId;
    private String          title;
    private byte[]          content;
    private boolean         top;
    private String          tag;
    private InvisibleStatus invisibleStatus;
    private RecordStatus    recordStatus;
    private Date            createTime;
    private Date            modifiedTime;
}
