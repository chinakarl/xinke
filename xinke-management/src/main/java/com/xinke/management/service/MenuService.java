package com.xinke.management.service;

import com.xinke.management.entity.beans.SysMenu;

import java.util.List;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/29
 */
public interface MenuService {

    /**
     *
     * 根据用户id获取左侧导航兰
     *
     * @author: zhaihx
     * @date: 17:20 2019/10/29
    **/
    List<SysMenu> getMenusByUserId();

}
