package sun.focusblog.admin.context;

/**
 * Created by root on 2015/11/19.
 */
public enum UserRole {
    ROLE_ADMIN(1),
    ROLE_USER(2);

    private int value;

    UserRole(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
