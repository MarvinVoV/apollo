package sun.focus.blog.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 2015/8/21.
 */

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
