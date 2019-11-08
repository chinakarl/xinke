package com.xinke.management.controller;

import com.github.pagehelper.PageInfo;
import com.xinke.management.entity.request.SysUserRequest;
import com.xinke.management.entity.beans.SysUser;
import com.xinke.management.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/29
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    SysUserService userService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public PageInfo getUserList(SysUserRequest reqSysUser)
    {
        return userService.getUsersByExample(reqSysUser);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void add(SysUser sysUser)
    {
        userService.addUser(sysUser);
    }

}
