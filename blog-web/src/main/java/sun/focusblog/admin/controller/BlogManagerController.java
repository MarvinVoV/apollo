package sun.focusblog.admin.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import sun.focusblog.admin.components.Helper;
import sun.focusblog.admin.components.pagination.Pagination;
import sun.focusblog.admin.domain.Article;
import sun.focusblog.admin.domain.Category;
import sun.focusblog.admin.domain.Comment;
import sun.focusblog.admin.domain.RelationType;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.*;
import sun.focusblog.utils.JSONBuilder;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by root on 2015/11/23.
 * <p/>
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

    @Autowired
    private UserService userService;

    @Autowired
    private RelationService relationService;

    @Autowired
    private CommentsService commentsService;

    private static final String MODEL_ATTR_RELATION = "relation";

    private static final String MODEL_ATTR_PAGINATION = "pagination";

    private static final String MODEL_ATTR_CATEGORIES = "categories";

    private static final String MODEL_ATTR_ARTICLES = "articles";

    private static final String MODEL_ATTR_ARTICLE = "article";

    private static final String MODEL_ATTR_COMMENTS = "comments";

    private static final String MODEL_ATTR_FOLLOWS = "follows"; // follow number


    /**
     * Category manager index page
     */
    @RequestMapping("index")
    public ModelAndView manager(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/manager");
        return modelAndView;
    }

    //#####################################################################################
    //                          Category
    //#####################################################################################

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
     * Personal blog home page
     */
    @RequestMapping(value = "blog/home", method = RequestMethod.GET)
    public ModelAndView personalBlogHome(ModelAndView modelAndView, HttpSession httpSession) {
        User user = Helper.getUser(httpSession);

        // Set categories
        List<Category> categories = categoryService.query(user);
        modelAndView.addObject(MODEL_ATTR_CATEGORIES, categories);

        // Set articles
        List<Article> articles = articleService.listOrderByDate(user, 1, Integer.valueOf(articleService.getPageSize()));
        modelAndView.addObject(MODEL_ATTR_ARTICLES, articles);

        // Set article number
        int count = articleService.countAll(user);
        Pagination pagination = new Pagination(count);
        pagination.setSize(Integer.valueOf(articleService.getPageSize()));
        modelAndView.addObject(MODEL_ATTR_PAGINATION, pagination);

        modelAndView.setViewName("admin/blogHome");
        return modelAndView;
    }


    //#####################################################################################
    //                          Article
    //#####################################################################################

    /**
     * Article list
     *
     * @param num          page num
     * @param modelAndView model and view
     * @param httpSession  session
     * @return model and view
     */
    @RequestMapping(value = "article/list", method = RequestMethod.GET)
    public ModelAndView listArticle(@RequestParam Integer num, ModelAndView modelAndView, HttpSession httpSession) {
        User user = Helper.getUser(httpSession);

        // Set categories
        List<Category> categories = categoryService.query(user);
        modelAndView.addObject(MODEL_ATTR_CATEGORIES, categories);

        // Set article number
        int count = articleService.countAll(user);
        Pagination pagination = new Pagination(count);
        pagination.setNum(num);
        pagination.setSize(Integer.valueOf(articleService.getPageSize()));
        modelAndView.addObject(MODEL_ATTR_PAGINATION, pagination);

        // Set articles
        List<Article> articles = articleService.listOrderByDate(user, pagination.getNum(), pagination.getSize());
        modelAndView.addObject(MODEL_ATTR_ARTICLES, articles);

        modelAndView.setViewName("admin/blogHome");
        return modelAndView;
    }

    /**
     * View article content
     *
     * @param id           article id
     * @param modelAndView model and view
     * @param httpSession  session
     * @return model and view
     */
    @RequestMapping(value = "article/view", method = RequestMethod.GET)
    public ModelAndView viewArticle(@RequestParam String id, @RequestParam String uid,
                                    @RequestParam(required = false) Integer num, ModelAndView modelAndView,
                                    HttpSession httpSession) {
        User user = Helper.getUser(httpSession);
        if (!StringUtils.isEmpty(uid)) {
            if (!uid.equals(user.getUserId())) {
                User currentUser = user;
                user = userService.query(uid);
                RelationType relationType = relationService.getRelation(uid, currentUser.getUserId());
                modelAndView.addObject(MODEL_ATTR_RELATION, relationType.toString());
            }
        }
        // Set follows
        modelAndView.addObject(MODEL_ATTR_FOLLOWS, relationService.follows(user.getUserId()));

        return prepareModelAttributes(modelAndView, "admin/blogDetail", id, this.commentsService, num, user);
    }


    /**
     * Prepare for modifying article
     *
     * @param id           article id
     * @param modelAndView mvc
     * @return mvc
     */
    @RequestMapping(value = "article/modify", method = RequestMethod.GET)
    public ModelAndView modifyArticleView(@RequestParam String id, ModelAndView modelAndView, HttpSession httpSession) {
        User user = Helper.getUser(httpSession);
        return prepareModelAttributes(modelAndView, "admin/modifyArticle", id, null, null, user);
    }

    /**
     * Inner Help method
     */
    public ModelAndView prepareModelAttributes(ModelAndView modelAndView, String view, String articleId,
                                               CommentsService commentsService, Integer pageNum, User user) {
        modelAndView.addObject("user", user);
        // Set categories
        List<Category> categories = categoryService.query(user);
        modelAndView.addObject(MODEL_ATTR_CATEGORIES, categories);
        // Set article
        Article article = articleService.query(articleId);
        modelAndView.addObject(MODEL_ATTR_ARTICLE, article);
        // Set comments
        if (commentsService != null) {
            int count = commentsService.count(articleId);
            Pagination pagination = new Pagination(count);
            pagination.setSize(commentsService.getPageSize());
            List<Comment> comments;
            if (pageNum != null) {
                pagination.setNum(pageNum);
                comments = commentsService.list(articleId, pageNum, commentsService.getPageSize());
            } else {
                pagination.setNum(1);
                comments = commentsService.defaultList(articleId);
            }
            modelAndView.addObject(MODEL_ATTR_PAGINATION, pagination);
            modelAndView.addObject(MODEL_ATTR_COMMENTS, comments);
        }
        modelAndView.setViewName(view);
        return modelAndView;
    }

    /**
     * Update article
     */
    @RequestMapping(value = "article/update", method = RequestMethod.POST)
    public ModelAndView updateArticle(@ModelAttribute Article article, BindingResult result, ModelAndView modelAndView,
                                      HttpSession httpSession) {
        if (result.hasErrors()) {
            logger.error("Binding article model encounter {} errors.", result.getFieldErrorCount());
        }
        User user = Helper.getUser(httpSession);

        boolean res = articleService.update(article, user);

        if (!res) {
            logger.error("update article {} failed.", article.getId());
        }
        RedirectView view = new RedirectView("/manager/article/view?id=" + article.getId());
        modelAndView.setView(view);
        return modelAndView;
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
        if (result.hasErrors()) {
            logger.error("Binding article model encounter {} errors.", result.getFieldErrorCount());
        }
        // UUID as Article primary key
        String id = UUID.randomUUID().toString();
        article.setId(id);

        articleService.saveWithInnerInfo(httpSession, article);

        Article babyArticle = articleService.query(id);
        modelAndView.addObject(MODEL_ATTR_ARTICLE, babyArticle);

        modelAndView.setViewName("admin/blogView");
        return modelAndView;
    }

    //#####################################################################################
    //                          Relation
    //#####################################################################################

    @RequestMapping(value = "user/follow", method = RequestMethod.POST, produces = {"application/json"})
    public
    @ResponseBody
    String follow(@RequestParam String uid, HttpSession httpSession) {
        String json = JSONBuilder.builder().inflate("status", "error").build().toString();
        if (StringUtils.isEmpty(uid)) {
            return json;
        }
        User user = Helper.getUser(httpSession);
        uid = new String(Base64Utils.decodeFromString(uid));
        relationService.follow(uid, user.getUserId());
        return JSONBuilder.builder().inflate("status", "ok").build().toString();
    }


    //#####################################################################################
    //                          Comment
    //#####################################################################################

    @RequestMapping(value = "comments/add", method = RequestMethod.POST)
    public String addComment(@ModelAttribute Comment comment, @RequestParam String uid, HttpSession httpSession) {
        User user = Helper.getUser(httpSession);

        boolean retVal = commentsService.save(comment, user);
        if (!retVal) {
            logger.error("save comment failed.");
        }
        return String.format("redirect:/manager/article/view?id=%s&uid=%s", comment.getArticleId(), uid);
    }

    @RequestMapping(value = "comments/reply", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String addReply(@ModelAttribute Comment comment, HttpSession httpSession) {
        User user = Helper.getUser(httpSession);

//        boolean retVal = commentsService.save(comment, user);
        boolean retVal = true;
        if (!retVal) {
            return JSONBuilder.builder().inflate("status", "error").build().toString();
        }
        return JSONBuilder.builder().inflate("status", "ok").build().toString();
    }


}
