package com.xinke.management.entity.beans;

import com.xinke.common.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/29
 */
public class SysUserSub extends BaseBean implements Serializable {

    private List<SysUser> result;

    private Long totalCount;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<SysUser> getResult() {
        return result;
    }

    public void setResult(List<SysUser> result) {
        this.result = result;
    }
}
