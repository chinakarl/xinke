package org.mybatis.generator.page;

import org.mybatis.generator.base.BaseEntity;

/**
 * @ClassName: PageCondition
 * @Description: 包含分页和排序两个基本查询条件的对象
 * @author zhx
 */
public class PageCondition extends BaseEntity
{

    /**
     * @Fields serialVersionUID : 版本序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields page : 分页查询的信息
     */
    protected Page page;

    /**
     * @Fields orderByClause : order by 后面的sql内容
     */
    protected String orderByClause;

    /**
     * <p>
     * Title: 空构造函数
     * </p>
     * <p>
     * Description: 构造函数
     * </p>
     */
    protected PageCondition() {
        page = null;
        orderByClause = null;
    }

    /**
     * <p>
     * Title: 分页构造函数
     * </p>
     * <p>
     * Description: 仅需要分页时使用该构造函数
     * </p>
     * @param page 分页对象
     */
    public PageCondition(final Page page) {
        this.page = page;
    }

    /**
     * <p>
     * Title: 排序构造函数
     * </p>
     * <p>
     * Description: 仅排序时，不需要分页时，使用该构造函数
     * </p>
     * @param orderByClause 排序
     */
    public PageCondition(final String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * <p>
     * Title: 分页排序构造函数
     * </p>
     * <p>
     * Description: 分页和排序同时需要时，使用该构造函数
     * </p>
     * @param page 分页
     * @param orderByClause 排序
     */
    public PageCondition(final Page page, final String orderByClause) {
        this.page = page;
        this.orderByClause = orderByClause;
    }

    /**
     * @Title: getPage
     * @Description: 返回分页条件
     * @return 分页条件
     */
    public Page getPage() {
        return this.page;
    }

    /**
     * @param page 分页查询信息<br>
     *        page.begin= 5,page.length=10; // 检索记录行 6-15
     */
    public void setPage(final Page page) {
        this.page = page;
    }

    /**
     * @Title: setOrderByClause
     * @Description: 设置order by后面的条件语句
     * @param orderByClause 例如 "a desc"
     */
    public void setOrderByClause(final String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * @Title: getOrderByClause
     * @Description: 获取order by 后面的条件语句
     * @return order by 后面的条件语句
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * @Title: clear
     * @Description: 重置查询条件类
     */
    public void clear() {
        orderByClause = null;
        page = null;
    }
}
