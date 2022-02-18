package com.github.alexscari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wusd
 */
@RestController
public class RestEchoController {

    private final RestTemplate restTemplate;

    @Autowired
    public RestEchoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/restecho/{str}")
    public String restecho(@PathVariable String str) {
        return restTemplate.getForObject("http://rest-provider/echo/" + str, String.class);
    }
}
