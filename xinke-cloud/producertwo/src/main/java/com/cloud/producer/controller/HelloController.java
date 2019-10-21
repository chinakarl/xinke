package com.cloud.producer.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/21
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello consul. two";
    }

}
