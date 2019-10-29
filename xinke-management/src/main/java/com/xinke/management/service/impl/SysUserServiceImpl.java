package com.xinke.management.service.impl;

import com.xinke.management.dao.SysUserMapper;
import com.xinke.management.entity.beans.SysUser;
import com.xinke.management.entity.beans.SysUserSub;
import org.mybatis.generator.criteria.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/29
 */

@Service
public class SysUserServiceImpl {

    @Autowired
    SysUserMapper sysUserMapper;

    public List<SysUser> getUsersByKeywords(SysUserSub sysUserSub) {

        Criteria example = SearchHelper.getCriteria(sysUserSub);
        Criteria.Condition condition = example.createConditon();
        return sysUserMapper.selectByExample(example);
    }

}
