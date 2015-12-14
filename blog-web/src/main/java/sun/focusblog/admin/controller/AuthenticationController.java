package sun.focusblog.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.focusblog.admin.components.EmailClient;
import sun.focusblog.admin.context.SessionConstants;
import sun.focusblog.admin.context.UserRole;
import sun.focusblog.admin.context.UserStatus;
import sun.focusblog.admin.domain.Article;
import sun.focusblog.admin.domain.auth.Function;
import sun.focusblog.admin.domain.auth.Role;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.ArticleService;
import sun.focusblog.admin.services.FunctionService;
import sun.focusblog.admin.services.RoleService;
import sun.focusblog.admin.services.UserService;

import javax.management.relation.RoleStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 2015/11/7.
 * <p/>
 * Authentication Center Controller. Control the login success, failed, logout request and
 * give the corresponding response.
 */

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/authentication/login", method = RequestMethod.GET)
    public ModelAndView indexPage(@RequestParam(value = "login", required = false) String login,
                                  @RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout,
                                  ModelAndView modelAndView) {
        if (login != null) {
            modelAndView.addObject("msg", "Login");
        }

        if (error != null) {
            modelAndView.addObject("msg", "Invalid username and password!");
        }

        if (logout != null) {
            modelAndView.addObject("msg", "Logout success.");
            modelAndView.setViewName("authentication/logout");
            return modelAndView;
        }
        modelAndView.setViewName("authentication/login");
        return modelAndView;
    }

    @RequestMapping(value = "/authentication/register", method = RequestMethod.GET)
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("authentication/register");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
    public ModelAndView loginSuccess(Principal principal, HttpSession httpSession, ModelAndView modelAndView) {
        String userId = principal.getName();
        User user = userService.query(userId);
        httpSession.setAttribute(SessionConstants.USER, user);

        // test
        Article article1 = articleService.query("e800854b-ea1a-4f80-a257-d0ae51ba24f8");
        Article article2 = articleService.query("48f408ba-a744-470b-86ce-e82177879e9d");
        List<Article> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        modelAndView.addObject("articles", articles);


        modelAndView.setViewName("admin/index");
        return modelAndView;
    }


    @RequestMapping(value = "/authentication/registerAction", method = RequestMethod.POST)
    public ModelAndView registerAction(@ModelAttribute User user, BindingResult result, ModelAndView modelAndView,
                                       HttpServletRequest request) {
        if (result.hasErrors()) {
            modelAndView.setViewName("/error/500");
        } else {
            modelAndView.setViewName("/authentication/register-valid");
        }

        String verifyKey = userService.cacheEmailVerifyKey(user);
        String address = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String verifyURL = address + request.getContextPath() + "/authentication/emailVerify?verifyKey=" + verifyKey;

        emailClient.sendHtmlEmail(user.getEmail(), "Focusblog", html(verifyURL));

        return modelAndView;
    }

    private String html(String verifyUrl) {
        StringBuilder sb = new StringBuilder();
        sb.append("<p> 这是来自Focusblog的验证邮件，请点击下面链接进行验证，该链接在24小时内有效，请及时验证</p>");
        sb.append("<a href=\"").append(verifyUrl).append("\">点击链接，进行邮箱验证</a>");
        sb.append("<p>如果点击链接无法进行跳转，请直接复制下面的链接内容到浏览器地址栏，进行访问</p>");
        sb.append("<div style=\"font-size:14px; font-color:#666\">").append(verifyUrl).append("</div>");
        return sb.toString();
    }

    @RequestMapping(value = "/authentication/emailVerify", method = RequestMethod.GET)
    public ModelAndView register(@RequestParam String verifyKey, ModelAndView modelAndView) {
        User user = userService.getVerifyUser(verifyKey);
        user.setCreateDate(new Date());
        user.setStatus(UserStatus.ACTIVE.getValue());
        user.setUserId(user.getUserName());

        user.getRoles().add(roleService.query(UserRole.ROLE_USER.getValue()));

        userService.saveUser(user);

        modelAndView.addObject("user", user);
        modelAndView.setViewName("/authentication/register-success");
        return modelAndView;
    }

}
