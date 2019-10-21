package com.cloud.producer.controller;

import com.cloud.producer.service.FeignHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/21
 */

@RestController
public class FeignHelloController {

    @Autowired
    private FeignHelloService feignHelloService;

    @RequestMapping("/feign/call")
    public String call() {
        // 像调用本地服务一样
        return feignHelloService.hello();
    }

}
