package com.xinke.management.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinke.common.mybatis.Criteria;
import com.xinke.management.dao.CategoryMapper;
import com.xinke.management.entity.beans.Category;
import com.xinke.management.entity.beans.SysUser;
import com.xinke.management.entity.beans.SysUserSub;
import com.xinke.management.entity.constants.TCategory;
import com.xinke.management.entity.request.CategoryRequest;
import com.xinke.management.entity.response.CategoryResponse;
import com.xinke.management.service.CategoryService;
import com.xinke.management.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/11/7
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getCategoriesByExample(CategoryRequest categoryRequest) {
        Criteria example = new Criteria();
        Criteria.Condition condition = example.createConditon();
        condition.andEqualTo(TCategory.PARENT_ID,categoryRequest.getParentId());
        //PageHelper.startPage(categoryRequest.getPageNo(),categoryRequest.getPageSize());
        List<CategoryResponse> categories = categoryMapper.selectByExample(example);
        //PageInfo<Category> pageInfo = new PageInfo(categories);
        return categories;
    }

    @Override
    public void addCategory(Category category)
    {
        category.setCreateTime(new Date());
        category.setCreateNo(UserUtil.getCurrentUser().getId());
        if(null == category.getParentId())
        {
            category.setParentId(0);
        }
        categoryMapper.insertSelective(category);
    }
}
