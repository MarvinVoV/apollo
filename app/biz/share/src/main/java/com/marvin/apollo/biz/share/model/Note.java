package com.marvin.apollo.biz.share.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hufeng
 * @version Note.java, v 0.1 2019-01-27 01:05 Exp $
 */

@Setter
@Getter
@Builder
public class Note implements Serializable {
    private String title;
    private int    bookId;
    private int    noteId;
    private String digest;
    private String tag;
    private String category;
    private String contentOfMd;
    private String contentOfHtml;
    private Date   updateTime;

}
