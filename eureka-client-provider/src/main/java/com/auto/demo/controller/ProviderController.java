package com.auto.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author wbs
 * @date 2020/3/28
 */
@RestController
public class ProviderController {

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return "Hello, " + name + " " + new Date();
    }
}
