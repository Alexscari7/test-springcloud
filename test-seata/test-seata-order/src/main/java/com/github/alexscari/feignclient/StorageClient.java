package com.github.alexscari.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wusd
 */
@FeignClient("seata-storage")
@RequestMapping("/storage")
public interface StorageClient {

    @PostMapping("/deduct")
    void deduct(@RequestParam("productId") long productId, @RequestParam("count") int count);

}
