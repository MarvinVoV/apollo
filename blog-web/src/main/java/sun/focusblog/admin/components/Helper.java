package sun.focusblog.admin.components;

import sun.focusblog.admin.context.SessionConstants;
import sun.focusblog.admin.domain.auth.User;

import javax.servlet.http.HttpSession;

/**
 * Created by root on 2015/11/25.
 */
public class Helper {
    /**
     * Inner helper method
     *
     * @param httpSession httpSession
     * @return current user
     */
    public static User getUser(HttpSession httpSession) {
        return (User) httpSession.getAttribute(SessionConstants.USER);

    }
}
