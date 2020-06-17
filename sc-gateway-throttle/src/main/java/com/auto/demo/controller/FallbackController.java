package com.auto.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wbs
 * @date 2020/3/29
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public String fallback() {
        return "fallback from gateway";
    }

}
