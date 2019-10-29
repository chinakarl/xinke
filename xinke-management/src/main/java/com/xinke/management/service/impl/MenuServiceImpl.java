package com.xinke.management.service.impl;

import com.xinke.management.dao.SysMenuMapper;
import com.xinke.management.entity.beans.SysMenu;
import com.xinke.management.service.MenuService;
import com.xinke.management.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/29
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> getMenusByUserId()
    {
        return menuMapper.getMenusByUserId(UserUtil.getCurrentUser().getId());
    }

}
