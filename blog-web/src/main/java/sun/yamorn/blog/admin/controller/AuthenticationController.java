package sun.yamorn.blog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by root on 2015/11/7.
 */

@Controller
public class AuthenticationController {

    @RequestMapping(value = "/authentication/login", method = RequestMethod.GET)
    public ModelAndView indexPage(@RequestParam(value = "login", required = false) String login,
                                  @RequestParam(value = "error", required = false) String error,
                                  ModelAndView modelAndView) {
        if (login != null) {
            modelAndView.addObject("msg", "Login");
        }

        if (error != null) {
            modelAndView.addObject("msg", "Invalid username and password!");
        }
        modelAndView.setViewName("authentication/login");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
    public String loginSuccess() {
        return "admin/index";
    }
//
//    @RequestMapping(value="/authentication/logout")
//    public String logout() {
//        return "authentication/logout";
//    }
}
