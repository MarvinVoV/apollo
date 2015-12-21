package sun.focusblog.admin.domain.sys;

/**
 * Created by root on 2015/12/21.
 */
public enum ResponseMsgStatus {
    OK,
    ERROR;

    @Override
    public String toString() {
        String retVal;
        switch (this) {
            case OK:
                retVal = "ok";
                break;
            case ERROR:
                retVal = "error";
                break;
            default:
                retVal = "ok";

        }
        return retVal;
    }
}
