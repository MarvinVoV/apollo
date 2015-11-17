package sun.focusblog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.focusblog.admin.domain.auth.User;

/**
 * Created by root on 2015/11/7.
 *
 * Authentication Center Controller. Control the login success, failed, logout request and
 * give the corresponding response.
 */

@Controller
public class AuthenticationController {

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

        if(logout != null){
            modelAndView.addObject("msg", "Logout success.");
            modelAndView.setViewName("authentication/logout");
            return modelAndView;
        }
        modelAndView.setViewName("authentication/login");
        return modelAndView;
    }

    @RequestMapping(value = "/authentication/register", method = RequestMethod.GET)
    public ModelAndView register(ModelAndView modelAndView){
        modelAndView.setViewName("authentication/register");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
    public String loginSuccess() {
        return "admin/index";
    }


    @RequestMapping(value = "/authentication/registerAction", method = RequestMethod.POST)
    public ModelAndView registerAction(@ModelAttribute User user, BindingResult result, ModelAndView modelAndView) {
        if(result.hasErrors()){
            modelAndView.setViewName("/error/500");
        }else{
            modelAndView.setViewName("/authentication/register-valid");
        }



        return null;
    }
}
