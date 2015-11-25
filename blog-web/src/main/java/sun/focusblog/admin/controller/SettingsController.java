package sun.focusblog.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.focusblog.admin.components.Helper;
import sun.focusblog.admin.context.SessionConstants;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.UserService;
import sun.focusblog.utils.JSONBuilder;

import javax.servlet.http.HttpSession;

/**
 * Created by root on 2015/11/19.
 */
@Controller
@RequestMapping("/setting")
public class SettingsController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public ModelAndView profile(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/settings");
        return modelAndView;
    }

    @RequestMapping(value = "updateHeader", method = RequestMethod.POST)
    @ResponseBody
    public String updateHeader(@ModelAttribute User user, HttpSession session) {
        User sessionUser = Helper.getUser(session);
        sessionUser.setHeader(user.getHeader());

        userService.updateHeader(sessionUser);

        return JSONBuilder.builder().inflate("status", "ok").build().toString();
    }

    @RequestMapping(value = "updateEmail", method = RequestMethod.POST)
    public String updateEmail(@ModelAttribute User user, HttpSession session) {
        User sessionUser = Helper.getUser(session);
        sessionUser.setEmail(user.getEmail());
        userService.updateEmail(sessionUser);
        return "redirect:/setting/profile";
    }
}
