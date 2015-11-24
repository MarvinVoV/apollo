package sun.focusblog.admin.services;

import sun.focusblog.admin.domain.Category;
import sun.focusblog.admin.domain.auth.User;

/**
 * Created by root on 2015/11/24.
 */
public interface CategoryService {

    boolean save(Category category, User currentUser);
}
