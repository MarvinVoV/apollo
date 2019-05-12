package com.marvin.apollo.common.dal.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Article Entity
 *
 * @author hufeng
 * @version $Id: ArticleEntity.java, v 0.1 2019年02月11日 下午6:13 hufeng Exp $
 */
@Getter
@Setter
public class ArticleEntity implements Serializable {
    /**
     * 自增ID
     */
    private long           id;
    /**
     * Lark的笔记ID
     */
    private int            refNoteId;
    /**
     * Lark的笔记本ID
     */
    private int            refBookId;
    /**
     * 标题
     */
    private String         title;
    /**
     * 摘要
     */
    private String         digest;
    /**
     * markdown 内容
     */
    private byte[]         contentOfMd;
    /**
     * HTML 内容
     */
    private byte[]         contentOfHtml;
    /**
     * 是否置顶 1 置顶，0 不置顶
     */
    private int            top;
    /**
     * 标签
     */
    private String         tag;
    /**
     * 是否隐藏 0 隐藏，1 不隐藏
     */
    private int            invisible;
    /**
     * 数据状态 0 无效， 1 生效
     *
     * @see com.marvin.apollo.core.model.enums.RecordStatus
     */
    private Integer        status;
    /**
     * 浏览量
     */
    private int            viewCount;
    /**
     * 点赞量
     */
    private int            likeCount;
    /**
     * 类目信息
     */
    private CategoryEntity categoryEntity;
    /**
     * 创建日期
     */
    private Date           gmtCreate;
    /**
     * 修改日期
     */
    private Date           gmtModified;
}
