package com.github.alexscari.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author wusd
 */
@FeignClient("seata-account")
@RequestMapping("/account")
public interface AccountClient {

    @PostMapping("/decrease")
    void decrease(@RequestParam("userId") long userId, @RequestParam("money") BigDecimal money);

}
