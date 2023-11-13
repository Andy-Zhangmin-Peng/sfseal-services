/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.athena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot Application
 *
 * @author WanYun
 */
@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"com.wanyun.sfseal"})
public class Application {

    @RequestMapping(value = {"/noauth/heartbeat"}, method = {RequestMethod.GET})
    public boolean healthCheck() {
        return true;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
