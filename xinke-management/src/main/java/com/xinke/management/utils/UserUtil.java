package com.xinke.management.utils;

import com.xinke.management.entity.beans.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/29
 */
public class UserUtil {

    public static SysUser getCurrentUser() {
        return (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
