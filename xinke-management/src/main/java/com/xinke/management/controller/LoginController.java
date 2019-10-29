package com.xinke.management.controller;

import com.xinke.common.base.Response;
import com.xinke.management.service.LoginService;
import com.xinke.management.service.impl.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.xinke.management.constants.Constants.SUCCESS_CODE;
import static com.xinke.management.constants.Constants.SUCCESS_MSG;

import com.xinke.common.*;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/23
 */

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    MyUserDetailService myUserDetailService;

    @PostMapping("/login")
    public Response login()
    {
        //Boolean isSuccess = loginService.authLogin(sysUser);
        Response response = new Response(SUCCESS_CODE, SUCCESS_MSG);

        return response;
    }

}
