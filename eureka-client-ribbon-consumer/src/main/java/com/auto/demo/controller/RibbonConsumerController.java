package com.auto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wbs
 * @date 2020/3/28
 */
@RestController
public class RibbonConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        name += " from ribbon consumer";
        //Spring Cloud Ribbon 有一个拦截器，它能够在这里进行实际调用的时候，自动的去选取服务实例，并将这里的服务名替换成实际要请求的 IP 地址和端口，从而完成服务接口的调用
        String url = "http://eureka-provider/sayHello/?name=" + name;
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }
}
