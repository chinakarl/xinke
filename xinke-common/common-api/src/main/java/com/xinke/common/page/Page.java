/**
 * @Title: Page.java
 * @Package com.lyl.core.dao.components
 * @Description: 分页组件的页面信息
 * @author sunjl
 * @version V1.0
 */
package com.xinke.common.page;

import com.ai.market.common.base.BaseEntity;

import java.io.Serializable;

/**
 * @ClassName: Page
 * @Description: 分页组件的页面信息
 * @author sunjl
 */
public class Page extends BaseEntity implements Serializable {

    /**
     * @Fields serialVersionUID : 默认序列版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields begin : 分页查询开始记录位置
     */
    private int begin;

    /**
     * @Fields end : 分页查看下结束位置
     */
    private int end;
    /**
     * @Fields length : 每页显示记录数
     */
    private int length;

    /**
     * @Fields count : 查询结果总记录数
     */
    private int count;

    /**
     * @Fields current : 当前页码
     */
    private int current;

    /**
     * @Fields total : 总共页数
     */
    private int total;

    /**
     * @Fields onlyCount : 只需要判断记录总数
     */
    private boolean onlyCount;

    /**
     * @Fields enableCount : 是否后台自动获取count，默认为false，即不获取count
     */
    private boolean enableCount;

    public Page() {
    }
    
    /**
     * 不知道总记录数的构造函数
     * @param begin 分页查询开始记录位置
     * @param length 每页显示记录数
     */
    public Page(final int begin, final int length) {
        this.begin = begin;
        this.length = length;
        end = this.begin + this.length;
        if (this.length != 0) {
            current = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;
        } else {
            current = 1;
        }
    }

    /**
     * 知道总记录数的构造函数
     * @param begin 分页查询开始记录位置
     * @param length 每页显示记录数
     * @param count 总记录数
     */
    public Page(final int begin, final int length, final int count) {
        this(begin, length);
        this.count = count;
    }

    /**
     * @return the begin
     */
    public int getBegin() {
        return begin;
    }

    /**
     * @return the end
     */
    public int getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(final int end) {
        this.end = end;
    }

    /**
     * @param begin the begin to set
     */
    public void setBegin(final int begin) {
        this.begin = begin;
        if (length > 0) {
            current = (int) Math.floor((this.begin * 1.0d) / length) + 1;
        } else {
            current = 1;
        }
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(final int length) {
        this.length = length;
        if (begin != 0) {
            if (this.length > 0) {
                current = (int) Math.floor((begin * 1.0d) / this.length) + 1;
            } else {
                current = 1;
            }
        }
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(final int count) {
        this.count = count;
        if (this.length > 0) {
            total = (int) Math.floor((this.count * 1.0d) / length);
            if ((this.count % length) != 0) {
                total = total + 1;
            }
        } else {
            total = 1;
        }
    }

    /**
     * @return the current
     */
    public int getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(final int current) {
        this.current = current;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        if (total == 0) {
            return 1;
        }
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(final int total) {
        this.total = total;
    }

    public boolean isOnlyCount() {
        return onlyCount;
    }

    public void setOnlyCount(boolean onlyCount) {
        this.onlyCount = onlyCount;
    }
    
    public boolean isEnableCount() {
        return enableCount;
    }

    public void setEnableCount(boolean enableCount) {
        this.enableCount = enableCount;
    }

    public static Page getPage(final int page, final int pageCount) {
        if(pageCount <= 0 || page < 1 )  return null;
        Page
            newPage = new Page((page - 1) * pageCount, pageCount);
        newPage.setEnableCount(true);
        return newPage;
    }
}
