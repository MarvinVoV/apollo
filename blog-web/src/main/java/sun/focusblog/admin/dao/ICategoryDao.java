package sun.focusblog.admin.dao;

import sun.focusblog.admin.domain.Category;

import java.util.List;

/**
 * Created by root on 2015/11/24.
 */
public interface ICategoryDao {
    String NAMESPACE = "sun.focusblog.admin.domain.Category";

    /**
     * Persist category entity
     *
     * @param category category entity
     * @return boolean
     */
    boolean save(Category category);

    /**
     * Query all category of this guy
     *
     * @param userId this guy's id
     * @return categories
     */
    List<Category> query(String userId);

    /**
     * Dynamic update category name,order,status fields
     *
     * @param category category entity
     * @return boolean
     */
    boolean update(Category category);

    /**
     * Delete category by id
     *
     * @param category category
     * @return boolean
     */
    boolean delete(Category category);
}
