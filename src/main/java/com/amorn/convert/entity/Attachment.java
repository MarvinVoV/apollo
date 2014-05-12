package com.amorn.convert.entity;

/**
 * Created by sunyameng on 2014/5/12.
 */
public class Attachment {
    private Long id;
    private String content;
    public Attachment(){}

    public Attachment(String content) {
        this.content=content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
