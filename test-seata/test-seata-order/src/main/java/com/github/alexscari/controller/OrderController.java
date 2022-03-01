package com.github.alexscari.controller;


import com.github.alexscari.entity.OrderEntity;
import com.github.alexscari.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wusd
 * @since 2022-02-28
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get/{id}")
    public OrderEntity get(@PathVariable String id) {
        OrderEntity entity = orderService.getById(id);
        return entity;
    }

    @GetMapping("/create")
    public void create(@RequestParam long userId, @RequestParam long productId,
                       @RequestParam int count, @RequestParam BigDecimal money) {
        orderService.create(userId, productId, count, money);
    }
}
