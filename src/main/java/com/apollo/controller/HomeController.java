package com.apollo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hufeng
 * @version HomeController.java, v 0.1 2019-01-08 00:28 Exp $
 */
@RestController
public class HomeController {
    @RequestMapping("/")
    String home() {
        return "hello world";
    }
}
