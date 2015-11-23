package sun.focusblog.admin.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.focusblog.admin.context.SessionConstants;
import sun.focusblog.admin.context.UserStatus;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.UserService;
import sun.focusblog.utils.BeanHelper;
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
    public String updateHeader(@ModelAttribute User user, HttpSession session){
        User sessionUser = (User)session.getAttribute(SessionConstants.USER);
        sessionUser.setHeader(user.getHeader());

        userService.updateHeader(sessionUser);

        return JSONBuilder.builder().inflate("status","ok").build().toString();
    }
}
