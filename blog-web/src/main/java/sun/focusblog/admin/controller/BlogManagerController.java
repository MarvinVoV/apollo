package sun.focusblog.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.focusblog.admin.context.SessionConstants;
import sun.focusblog.admin.domain.Category;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.CategoryService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by root on 2015/11/23.
 */
@Controller
@RequestMapping("/manager")
public class BlogManagerController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("index")
    public ModelAndView manager(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/manager");
        return modelAndView;
    }

    @RequestMapping("category")
    public ModelAndView category(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/category");
        return modelAndView;
    }

    @RequestMapping(value = "category/save", method = RequestMethod.POST)
    public String saveCategory(@ModelAttribute Category category,HttpSession session) {
        User user = (User) session.getAttribute(SessionConstants.USER);
        category.setUserId(user.getUserId());
        category.setCreateDate(new Date());
        categoryService.save(category, user);
        return "redirect:/manager/category";
    }
}
