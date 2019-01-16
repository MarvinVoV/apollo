package com.marvin.lab.oauth.resource.server.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hufeng
 * @version ResourceServerApplication.java, v 0.1 2019-01-17 00:55 Exp $
 */
@SpringBootApplication(scanBasePackages = "com.marvin.lab")
public class ResourceServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }
}
