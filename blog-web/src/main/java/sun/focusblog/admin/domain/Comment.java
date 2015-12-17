package sun.focusblog.admin.domain;

import sun.focusblog.admin.domain.auth.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 2015/12/16.
 *
 * Article Comment
 */
public class Comment {
    private int id;
    private String articleId;
    private String content;
    private Date commentDate;
    private User user;
    private Comment parent;
    private List<Comment> children = new LinkedList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }
}
