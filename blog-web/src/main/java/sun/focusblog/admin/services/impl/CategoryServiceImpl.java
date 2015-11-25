package sun.focusblog.admin.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.focusblog.admin.dao.ICategoryDao;
import sun.focusblog.admin.domain.Category;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.CategoryService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 2015/11/24.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(Category category, User currentUser) {
        category.setUserId(currentUser.getUserId());
        category.setCreateDate(new Date());
        return categoryDao.save(category);
    }

    @Override
    public List<Category> query(User user) {
        if (user == null || StringUtils.isEmpty(user.getUserId())) {
            return new ArrayList<>();
        }
        return categoryDao.query(user.getUserId());
    }

    @Override
    public boolean update(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public boolean delete(int id) {
        Category category = new Category();
        category.setId(id);
        return categoryDao.delete(category);
    }
}
