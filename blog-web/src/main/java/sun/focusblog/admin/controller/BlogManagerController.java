package sun.focusblog.admin.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.focusblog.admin.components.Helper;
import sun.focusblog.admin.domain.Category;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.CategoryService;
import sun.focusblog.utils.JSONBuilder;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    public ModelAndView category(ModelAndView modelAndView, HttpSession session) {
        List<Category> list = categoryService.query(Helper.getUser(session));
        modelAndView.addObject("list", list);
        modelAndView.setViewName("/admin/category");
        return modelAndView;
    }

    @RequestMapping(value = "category/save", method = RequestMethod.POST)
    public String saveCategory(@ModelAttribute Category category, HttpSession session) {
        User user = Helper.getUser(session);
        category.setUserId(user.getUserId());
        category.setCreateDate(new Date());
        categoryService.save(category, user);
        return "redirect:/manager/category";
    }

    /**
     * update category name
     *
     * @param category category
     * @return json
     */
    @RequestMapping(value = "category/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateCategory(@ModelAttribute Category category) {
        categoryService.update(category);
        return JSONBuilder.builder().inflate("status", "ok").build().toString();
    }

    @RequestMapping(value = "category/delete", method = RequestMethod.GET)
    public String deleteCategory(@RequestParam String id) {
        if (!StringUtils.isEmpty(id)) {
            categoryService.delete(Integer.valueOf(id));
        }
        return "redirect:/manager/category";
    }

    @RequestMapping(value = "category/sort", method = RequestMethod.POST)
    @ResponseBody
    public String sortCategory(@RequestBody Category[] categories) {
        for (Category category : categories) {
            categoryService.update(category);
        }
        return JSONBuilder.builder().inflate("status", "ok").build().toString();
    }
}
