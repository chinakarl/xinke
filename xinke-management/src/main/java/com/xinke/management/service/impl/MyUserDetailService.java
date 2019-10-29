package com.xinke.management.service.impl;

import com.xinke.management.dao.SysUserMapper;
import com.xinke.management.entity.beans.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/25
 */

@Service
@Transactional
public class MyUserDetailService implements UserDetailsService {

   @Autowired
   SysUserMapper hrMapper;

    /**
     * 登陆验证时，通过username获取用户的所有权限信息
     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = hrMapper.selectUserByName(username);
        if(null == sysUser)
        {
            throw new UsernameNotFoundException("未找到用户!");
        }
        return sysUser;
    }

}
