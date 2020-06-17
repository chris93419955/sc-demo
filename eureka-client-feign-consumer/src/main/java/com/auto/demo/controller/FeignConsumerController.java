package com.auto.demo.controller;

import com.auto.demo.define.SayHelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wbs
 * @date 2020/3/28
 */
@RestController
public class FeignConsumerController {

    @Autowired
    private SayHelloRemote remote;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        name += " from feign consumer";

        return remote.Hello(name);
    }
}
