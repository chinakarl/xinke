package com.cloud.producer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/21
 */

@FeignClient(name = "service-producer", fallback = FeignHelloHystrix.class)
public interface FeignHelloService {

    @RequestMapping("/hello")
    public String hello();

}
