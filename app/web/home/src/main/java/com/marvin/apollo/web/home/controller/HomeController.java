package com.marvin.apollo.web.home.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @author hufeng
 * @version HomeController.java, v 0.1 2019-01-11 01:56 Exp $
 */
@RestController
public class HomeController {
    @GetMapping("/")
    public String home(HttpSession httpSession) {

        return "welcome " + httpSession.getId();
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
