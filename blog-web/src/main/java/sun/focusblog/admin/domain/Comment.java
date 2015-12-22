package sun.focusblog.admin.domain;

import sun.focusblog.admin.domain.auth.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 2015/12/16.
 * <p/>
 * Article Comment
 */
public class Comment {
    private String id; // uuid
    private String articleId;
    private String content;
    private Date date;
    private User user;
    private Comment parent;
    private List<Comment> comments = new LinkedList<>();    // reply comments, json format

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
