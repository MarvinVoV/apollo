package sun.focusblog.admin.dao;

import sun.focusblog.admin.domain.Category;

/**
 * Created by root on 2015/11/24.
 */
public interface ICategoryDao {
    String NAMESPACE = "sun.focusblog.admin.domain.Category";

    boolean save(Category category);
}
