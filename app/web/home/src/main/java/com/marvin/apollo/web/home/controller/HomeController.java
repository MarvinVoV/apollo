package com.marvin.apollo.web.home.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hufeng
 * @version HomeController.java, v 0.1 2019-01-11 01:56 Exp $
 */
@RestController
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "welcome";
    }
}
