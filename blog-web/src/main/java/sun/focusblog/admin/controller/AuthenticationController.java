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
import sun.focusblog.admin.context.UserRole;
import sun.focusblog.admin.context.UserStatus;
import sun.focusblog.admin.domain.auth.Function;
import sun.focusblog.admin.domain.auth.Role;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.FunctionService;
import sun.focusblog.admin.services.RoleService;
import sun.focusblog.admin.services.UserService;

import javax.management.relation.RoleStatus;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    public String loginSuccess() {
        return "admin/index";
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
        String html = "<a href=\"" + verifyURL + "\">点击链接，进行邮箱验证</a>";

        emailClient.sendHtmlEmail(user.getEmail(), "Focusblog", html);

        return modelAndView;
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
