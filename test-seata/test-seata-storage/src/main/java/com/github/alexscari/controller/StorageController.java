package com.github.alexscari.controller;


import com.github.alexscari.service.StorageService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wusd
 * @since 2022-03-01
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    private StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/deduct")
    public void deduct(@RequestParam Long productId, @RequestParam int count) {
        storageService.deduct(productId, count);
    }
}
