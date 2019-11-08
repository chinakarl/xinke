package com.xinke.management.service;

import com.github.pagehelper.PageInfo;
import com.xinke.management.entity.request.SysUserRequest;
import com.xinke.management.entity.beans.SysUser;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/29
 */
public interface SysUserService {

    /**
     *
     * 根据条件获取用户信息
     *
     * @author: zhaihx
     * @date: 9:52 2019/10/30
    **/
    PageInfo getUsersByExample(SysUserRequest reqSysUser);

    void addUser(SysUser sysUser);

}
