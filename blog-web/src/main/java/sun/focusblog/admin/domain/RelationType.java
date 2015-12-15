package sun.focusblog.admin.domain;

/**
 * Created by root on 2015/12/15.
 * <p/>
 * relation type
 */
public enum RelationType {
    FOLLOWER(1),
    BLACKLIST(2),
    NOTHING(0);

    private int value;

    RelationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        String val;
        switch (this) {
            case FOLLOWER:
                val = "FOLLOWER";
                break;
            case BLACKLIST:
                val = "BLACKLIST";
                break;
            case NOTHING:
                val = "NOTHING";
                break;
            default:
                val = "NOTHING";
        }
        return val;
    }
}
