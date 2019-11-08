package com.xinke.management.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinke.common.base.SearchHelper;
import com.xinke.common.mybatis.Criteria;
import com.xinke.management.dao.SysUserMapper;
import com.xinke.management.entity.request.SysUserRequest;
import com.xinke.management.entity.beans.SysUser;
import com.xinke.management.entity.beans.SysUserSub;
import com.xinke.management.entity.constants.TSysUser;
import com.xinke.management.service.SysUserService;
import com.xinke.management.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/29
 */

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public PageInfo getUsersByExample(SysUserRequest reqSysUser) {

        SysUserSub sysUserSub = new SysUserSub();
        Criteria example = SearchHelper.getCriteria(sysUserSub);
        Criteria.Condition condition = example.createConditon();
        if(StringUtils.isNotEmpty(reqSysUser.getName()))
        {
            condition.andLike(TSysUser.NAME,reqSysUser.getName());
        }
        PageHelper.startPage(reqSysUser.getPageNo(),reqSysUser.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUsers);
        return pageInfo;
    }

    @Override
    public void addUser(SysUser sysUser)
    {
        sysUser.setCreateTime(new Date());
        sysUser.setCreateNo(UserUtil.getCurrentUser().getId());
        sysUserMapper.insertSelective(sysUser);
    }

}
