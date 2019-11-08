package com.xinke.management.entity.beans;

import org.mybatis.generator.base.BaseBean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Category
 * @Description: category表对应的java bean类
 * @author: Interests
 */
public class Category extends BaseBean implements Serializable {
    /**
     * @Fields serialVersionUID : 自动生成默认序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields category.id :主键
     */
    private Integer id;

    /**
     * @Fields category.category_name :类别的名称
     */
    private String categoryName;

    /**
     * @Fields category.category_code :类别编码
     */
    private String categoryCode;

    /**
     * @Fields category.parent_id :父级id，顶级默认为0
     */
    private Integer parentId;

    /**
     * @Fields category.hierarchy :层级，从1开始，如果父类层级为1，子类就为2
     */
    private Integer hierarchy;

    /**
     * @Fields category.create_time :创建时间
     */
    private Date createTime;

    /**
     * @Fields category.update_time :更新时间
     */
    private Date updateTime;

    /**
     * @Fields category.create_no :创建人号
     */
    private Integer createNo;

    /**
     * @Fields category.update_no :更新人
     */
    private Integer updateNo;

    private boolean delFlag;

    private boolean isParent;

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    /**
     * @return category.id : 返回 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id of category : 设置 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return category.category_name : 返回 类别的名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName of category : 设置 类别的名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    /**
     * @return category.category_code : 返回 类别编码
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * @param categoryCode of category : 设置 类别编码
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    /**
     * @return category.parent_id : 返回 父级id，顶级默认为0
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId of category : 设置 父级id，顶级默认为0
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return category.hierarchy : 返回 层级，从1开始，如果父类层级为1，子类就为2
     */
    public Integer getHierarchy() {
        return hierarchy;
    }

    /**
     * @param hierarchy of category : 设置 层级，从1开始，如果父类层级为1，子类就为2
     */
    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     * @return category.create_time : 返回 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime of category : 设置 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return category.update_time : 返回 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime of category : 设置 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return category.create_no : 返回 创建人号
     */
    public Integer getCreateNo() {
        return createNo;
    }

    /**
     * @param createNo of category : 设置 创建人号
     */
    public void setCreateNo(Integer createNo) {
        this.createNo = createNo;
    }

    /**
     * @return category.update_no : 返回 更新人
     */
    public Integer getUpdateNo() {
        return updateNo;
    }

    /**
     * @param updateNo of category : 设置 更新人
     */
    public void setUpdateNo(Integer updateNo) {
        this.updateNo = updateNo;
    }
}