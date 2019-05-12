package com.marvin.apollo.core.model.dto;

import com.marvin.apollo.core.model.enums.InvisibleStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Article DTO
 *
 * @author hufeng
 * @version ArticleDto.java, v 0.1 2019-01-13 20:56 Exp $
 */
@Setter
@Getter
@ToString
public class ArticleDto implements Serializable {
    /**
     * 标识ID
     */
    private Long            id;
    /**
     * 类目ID
     */
    private Long            categoryId;
    /**
     * Lark的笔记ID
     */
    private int             refNoteId;
    /**
     * Lark的笔记本ID
     */
    private int             refBookId;
    /**
     * 类目名称
     */
    private String          categoryName;
    /**
     * 标题
     */
    private String          title;
    /**
     * 摘要
     */
    private String          digest;
    /**
     * Markdown 内容
     */
    private String          contentOfMd;
    /**
     * HTML 内容
     */
    private String          contentOfHtml;
    /**
     * 是否置顶
     */
    private boolean         top;
    /**
     * 标签
     */
    private String          tag;
    /**
     * 浏览量
     */
    private int             viewCount;
    /**
     * 点赞量
     */
    private int             likeCount;
    /**
     * 隐私控制
     */
    private InvisibleStatus invisibleStatus;
    /**
     * 创建时间
     */
    private Date            createTime;
    /**
     * 修改时间
     */
    private Date            modifiedTime;
}
