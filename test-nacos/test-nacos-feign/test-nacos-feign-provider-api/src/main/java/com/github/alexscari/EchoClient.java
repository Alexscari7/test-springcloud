package com.github.alexscari;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wusd
 */
@FeignClient(value = "feign-provider")
public interface EchoClient {

    @GetMapping("/echo/{str}")
    String echo(@PathVariable("str") String str);

}
