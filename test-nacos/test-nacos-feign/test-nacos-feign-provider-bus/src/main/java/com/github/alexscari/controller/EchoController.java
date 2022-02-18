package com.github.alexscari.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author wusd
 */
@RestController
public class EchoController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/echo/{str}")
    public String echo(@PathVariable String str) throws UnknownHostException {
        return "Hello Nacos-Feign Discovery, " + str + ", source ip:" + InetAddress.getLocalHost() + ":" + port;
    }

}

