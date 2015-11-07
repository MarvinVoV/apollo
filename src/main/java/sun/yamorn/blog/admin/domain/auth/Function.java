package sun.yamorn.blog.admin.domain.auth;

/**
 * Created by root on 2015/11/7.
 */
public class Function extends BaseFunction {
    /**
     * this field help to query sub function as a query condition
     */
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
