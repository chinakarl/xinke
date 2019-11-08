package com.xinke.management.service;

import com.github.pagehelper.PageInfo;
import com.xinke.management.entity.beans.Category;
import com.xinke.management.entity.request.CategoryRequest;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/11/7
 */
public interface CategoryService {

    PageInfo getCategoriesByExample(CategoryRequest categoryRequest);

    void addCategory(Category category);
}
