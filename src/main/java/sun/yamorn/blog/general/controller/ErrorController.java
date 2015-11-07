package sun.yamorn.blog.general.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 2015/11/7.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping(value = "404")
    public String error404() {
        return "error/404";
    }

    @RequestMapping(value = "500")
    public String error500() {
        return "error/500";
    }

    @RequestMapping(value = "403")
    public String error403() {
        return "error/403";
    }

    @RequestMapping(value = "expire")
    public String expire() {
        return "error/expire";
    }
}
