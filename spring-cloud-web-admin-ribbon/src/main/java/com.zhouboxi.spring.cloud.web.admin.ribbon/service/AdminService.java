package com.zhouboxi.spring.cloud.web.admin.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {

    @Autowired
    private RestTemplate restTemplate;

    //指定熔断方法
    @HystrixCommand(fallbackMethod = "hiError" )
    public String sayHi(String message) {
        return restTemplate.getForObject("http://spring-cloud-service-admin/hi?message=" + message, String.class);
    }

    //熔断方法
    public String hiError(String message) {
        return "Hi，your message is :\"" + message + "\" but request error.";
    }
}
