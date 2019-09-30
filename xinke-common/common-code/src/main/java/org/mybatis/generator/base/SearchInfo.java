package org.mybatis.generator.base;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.page.Page;

/**
 * Created by zhx on 19/9/26.
 */
public class SearchInfo extends BaseEntity
{

    public static final String SORT_ORDER_ASC = "asc";

    public static final String SORT_ORDER_DESC = "desc";

    /**
     * 第几页
     */
    private Integer page;

    /**
     * 每页条数
     */
    private int pageCount;

    /**
     * 分页开始记录数
     */
    private Integer start;

    /**
     * 是否只获取数据总条数
     */
    private boolean onlyCount = false;

    /**
     * 是否需要获取数据总条数
     */
    private boolean enableCount = true;

    /**
     * 排序字段名
     */
    private String sortByName;

    /**
     * 排序方式（"asc", "desc"）
     */
    private String sortByOrder;

    /**
     * 排序语句，可用于根据多个字段排序
     */
    private String orderByClause;


    private Page _page;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getSortByName() {
        return sortByName;
    }

    public void setSortByName(String sortByName) {
        this.sortByName = sortByName;
    }

    public String getSortByOrder() {
        return sortByOrder;
    }

    public void setSortByOrder(String sortByOrder) {
        this.sortByOrder = sortByOrder;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isEnableCount() {
        return enableCount;
    }

    public void setEnableCount(boolean enableCount) {
        this.enableCount = enableCount;
    }

    public boolean isOnlyCount() {
        return onlyCount;
    }

    public void setOnlyCount(boolean onlyCount) {
        this.onlyCount = onlyCount;
    }

    /**
     * 设置默认排序方式
     * @Title: defaultOrderBy
     * @Description:
     * @param sortByName
     * @param sortByOrder
     */
    public void defaultOrderBy(String sortByName, String sortByOrder) {
        if(StringUtils.isBlank(getSortByName())) {
            setSortByName(sortByName);
            setSortByOrder(sortByOrder);
        }
    }

    /**
     * 设置默认排序语句
     * @param orderByClause
     */
    public void defaultOrderBy(String orderByClause) {
        if(StringUtils.isBlank(getOrderByClause())) {
            setOrderByClause(orderByClause);
        }
    }

    void bindPage(Page _page) {
        this._page = _page;
    }

    public Integer totalCount() {
        if(null == _page) return null;
        return _page.getCount();
    }
}
