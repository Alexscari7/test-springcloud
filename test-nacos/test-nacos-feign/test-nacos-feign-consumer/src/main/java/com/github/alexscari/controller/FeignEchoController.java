package com.github.alexscari.controller;

import com.github.alexscari.EchoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wusd
 */
@RestController
public class FeignEchoController {

    private final EchoClient echoClient;

    @Autowired
    public FeignEchoController(EchoClient echoClient) {
        this.echoClient = echoClient;
    }

    @GetMapping("/feignecho/{str}")
    public String feignecho(@PathVariable String str) {
        return echoClient.echo(str);
    }

}
