package com.marvin.lab.oauth.authorization.server.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hufeng
 * @version AuthorizationServerApplication.java, v 0.1 2019-01-16 23:30 Exp $
 */
@SpringBootApplication(scanBasePackages = "com.marvin.lab")
public class AuthorizationServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }
}
