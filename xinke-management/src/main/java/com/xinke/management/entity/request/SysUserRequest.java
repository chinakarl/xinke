package com.xinke.management.entity.request;

import java.io.Serializable;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/11/5
 */
public class SysUserRequest extends BaseRequest implements Serializable {

    private String  name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
