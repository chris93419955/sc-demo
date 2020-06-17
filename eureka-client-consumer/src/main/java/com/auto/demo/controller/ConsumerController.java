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
public class ConsumerController {

    @Autowired
    private LoadBalancerClient client;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        name += " from consumer";
        //choose 方法来负载均衡的选出一个 eureka-provider 的服务实例
        ServiceInstance instance = client.choose("eureka-provider");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/sayHello/?name=" + name;
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }
}
