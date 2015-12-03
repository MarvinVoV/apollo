package sun.focusblog.admin.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.focusblog.admin.components.Helper;
import sun.focusblog.admin.domain.Article;
import sun.focusblog.admin.domain.Category;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.ArticleService;
import sun.focusblog.admin.services.CategoryService;
import sun.focusblog.utils.JSONBuilder;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 2015/11/23.
 *
 * Blog manager and post new blog controller.
 */
@Controller
@RequestMapping("/manager")
public class BlogManagerController {
    private static final Logger logger = LoggerFactory.getLogger(BlogManagerController.class);
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    /**
     * Category manager index page
     */
    @RequestMapping("index")
    public ModelAndView manager(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/manager");
        return modelAndView;
    }

    /**
     * List all category
     */
    @RequestMapping("category")
    public ModelAndView category(ModelAndView modelAndView, HttpSession session) {
        List<Category> list = categoryService.query(Helper.getUser(session));
        modelAndView.addObject("list", list);
        modelAndView.setViewName("/admin/category");
        return modelAndView;
    }

    /**
     * Add new category
     */
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

    /**
     * Delete category
     */
    @RequestMapping(value = "category/delete", method = RequestMethod.GET)
    public String deleteCategory(@RequestParam String id) {
        if (!StringUtils.isEmpty(id)) {
            categoryService.delete(Integer.valueOf(id));
        }
        return "redirect:/manager/category";
    }

    /**
     * Update category manager sequence
     */
    @RequestMapping(value = "category/sort", method = RequestMethod.POST)
    @ResponseBody
    public String sortCategory(@RequestBody Category[] categories) {
        for (Category category : categories) {
            categoryService.update(category);
        }
        return JSONBuilder.builder().inflate("status", "ok").build().toString();
    }

    /**
     * Post new article
     */
    @RequestMapping(value = "article/new", method = RequestMethod.GET)
    public ModelAndView writeArticle(ModelAndView modelAndView, HttpSession httpSession) {
        List<Category> list = categoryService.query(Helper.getUser(httpSession));
        modelAndView.addObject("list", list);
        modelAndView.setViewName("admin/writeArticle");
        return modelAndView;
    }

    @RequestMapping(value = "article/post", method = RequestMethod.POST)
    public ModelAndView post(@ModelAttribute Article article, BindingResult result, ModelAndView modelAndView,
                             HttpSession httpSession) {
        if(result.hasErrors()){
            logger.error("Binding article model encounter {} errors.", result.getFieldErrorCount());
        }
        articleService.saveWithInnerInfo(httpSession, article);
        modelAndView.setViewName("admin/blogHome");
        return modelAndView;
    }
}
