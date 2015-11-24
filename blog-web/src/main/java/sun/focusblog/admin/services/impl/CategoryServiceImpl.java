package sun.focusblog.admin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.focusblog.admin.dao.ICategoryDao;
import sun.focusblog.admin.domain.Category;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.CategoryService;

import java.util.Date;

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
}
