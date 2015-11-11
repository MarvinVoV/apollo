package sun.yamorn.blog.admin.domain.auth;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 2015/11/7.
 */
public interface IUser {

    String getUserId();

    String getUserName();

    String getPassword();

    Date getCreateDate();

    Boolean isEnable();

    List<IFunction> getFunctions();

    List<IRole> getRoles();
}
