package com.auto.demo.define;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wbs
 * @date 2020/3/28
 */
@FeignClient(name="eureka-provider")
public interface SayHelloRemote {

    @GetMapping("/sayHello")
    String Hello(@RequestParam(value = "name") String name);
}
