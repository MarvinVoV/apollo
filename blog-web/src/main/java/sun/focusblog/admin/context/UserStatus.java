package sun.focusblog.admin.context;

/**
 * Created by root on 2015/11/19.
 * <p/>
 * user status
 */
public enum UserStatus {
    ACTIVE(1),
    DISABLE(2);

    private int value;

    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
