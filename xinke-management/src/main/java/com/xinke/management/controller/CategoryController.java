package com.xinke.management.controller;

import com.github.pagehelper.PageInfo;
import com.xinke.management.entity.beans.Category;
import com.xinke.management.entity.request.CategoryRequest;
import com.xinke.management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/11/7
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public PageInfo getUserList(CategoryRequest categoryRequest)
    {
        return categoryService.getCategoriesByExample(categoryRequest);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addCategory(Category category)
    {
         categoryService.addCategory(category);
    }
}
