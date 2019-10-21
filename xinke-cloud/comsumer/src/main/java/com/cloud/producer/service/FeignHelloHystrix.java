package com.cloud.producer.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/21
 */

@Component
public class FeignHelloHystrix implements  FeignHelloService {

    @RequestMapping("/hello")
    public String hello() {
        return "sorry, this service call failed.";
    }

}
