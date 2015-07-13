package com.yamorn.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunyameng on 2014/5/12.
 */
public class Article {
    private Long id;
    private String title;
    private List<Attachment> attachments=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
