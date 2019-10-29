package com.xinke.management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/29
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/list")
    public void getUserList()
    {

    }

}
