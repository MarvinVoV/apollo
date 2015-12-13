package sun.focusblog.admin.domain;

import java.util.Date;

/**
 * Created by root on 2015/12/3.
 * <p/>
 * Article entity
 */
public class Article {
    private String id; // uuid
    private String userId;
    private int categoryId;
    private String categoryName; // associate field
    private String title;
    private String type;
    private Date createDate;
    private Date updateDate;
    private long pageView;
    private int order;
    private String content;
    private String tags;
    private String reference;
    private String digest;
    private boolean isHide;
    private boolean allowComment;
    private boolean isTop;
    private boolean autoIndex;

    public boolean isAutoIndex() {
        return autoIndex;
    }

    public void setAutoIndex(boolean autoIndex) {
        this.autoIndex = autoIndex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public long getPageView() {
        return pageView;
    }

    public void setPageView(long pageView) {
        this.pageView = pageView;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public boolean getIsHide() {
        return isHide;
    }

    public void setIsHide(boolean hide) {
        isHide = hide;
    }

    public boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(boolean allowComment) {
        this.allowComment = allowComment;
    }

    public boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(boolean top) {
        isTop = top;
    }
}
