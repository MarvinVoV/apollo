package sun.focusblog.admin.services;

import sun.focusblog.admin.domain.Category;
import sun.focusblog.admin.domain.auth.User;

import java.util.List;

/**
 * Created by root on 2015/11/24.
 */
public interface CategoryService {

    /**
     * Persist category
     *
     * @param category    category entity
     * @param currentUser current user
     * @return boolean
     */
    boolean save(Category category, User currentUser);

    /**
     * Query all category of this guy. no pagination
     *
     * @param user this guy
     * @return list
     */
    List<Category> query(User user);

    /**
     * Dynamic update category
     *
     * @param category category
     * @return boolean
     */
    boolean update(Category category);

    /**
     * Delete category by id
     *
     * @param id category id
     * @return boolean
     */
    boolean delete(int id);
}
