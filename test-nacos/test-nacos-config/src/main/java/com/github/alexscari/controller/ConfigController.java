package com.github.alexscari.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wusd
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${user}")
    private String user;

    @Value(("${shared}"))
    private String common;

    @GetMapping("/user")
    public String getUser() {
        return "Hello Nacos Config, " + user;
    }

    @GetMapping("/common")
    public String getCommon() {
        return "Hello Nacos Shared Config, " + common;
    }

}
