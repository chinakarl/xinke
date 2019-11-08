package com.xinke.management.entity.request;

import java.io.Serializable;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/11/7
 */
public class CategoryRequest extends BaseRequest implements Serializable {

    private Integer parentId;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
