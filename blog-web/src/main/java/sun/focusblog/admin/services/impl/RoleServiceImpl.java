package sun.focusblog.admin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.focusblog.admin.dao.IRoleDao;
import sun.focusblog.admin.domain.auth.Role;
import sun.focusblog.admin.services.RoleService;

/**
 * Created by root on 2015/11/19.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public Role query(int roleId) {
        return roleDao.query(roleId);
    }
}
