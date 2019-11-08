package com.xinke.management.entity.request;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/11/7
 */
public class BaseRequest {

    /**
     * 总数
     */
    private int  totalCount;
    /**
     * 页大小
     */
    private int  pageSize;
    /**
     * 当前页
     */
    private int  pageNo;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
