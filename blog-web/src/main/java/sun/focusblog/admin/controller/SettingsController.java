package sun.focusblog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by root on 2015/11/19.
 */
@Controller
@RequestMapping("/setting")
public class SettingsController {

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public ModelAndView profile(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/settings");
        return modelAndView;
    }
}
