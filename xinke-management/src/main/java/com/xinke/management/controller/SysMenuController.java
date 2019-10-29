package com.xinke.management.controller;

import com.xinke.management.entity.beans.SysMenu;
import com.xinke.management.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/29
 */

@RestController
public class SysMenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/getmenus")
    public List<SysMenu> sysmenu() {
        return menuService.getMenusByUserId();
    }
}
