package sun.focusblog.admin.services;

import sun.focusblog.admin.domain.auth.Role;

/**
 * Created by root on 2015/11/19.
 */
public interface RoleService {
    Role query(int roleId);
}
