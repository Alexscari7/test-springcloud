package com.github.alexscari.controller;

import com.github.alexscari.entity.OrderEntity;
import com.github.alexscari.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wusd
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final OrderService orderService;

    @Autowired
    public TestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getOrder/{id}")
    public OrderEntity get(@PathVariable String id) {
        OrderEntity entity = orderService.getById(id);
        return entity;
    }
}
