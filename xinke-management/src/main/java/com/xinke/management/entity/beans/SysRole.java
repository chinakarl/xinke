package com.xinke.management.entity.beans;

import org.mybatis.generator.base.BaseBean;

import java.io.Serializable;

/**
 * @ClassName: SysRole
 * @Description: sys_role表对应的java bean类
 * @author: Interests
 */
public class SysRole extends BaseBean implements Serializable {
    /**
     * @Fields serialVersionUID : 自动生成默认序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields sys_role.id :
     */
    private Integer id;

    /**
     * @Fields sys_role.name :
     */
    private String name;

    /**
     * @Fields sys_role.nameZh :角色名称
     */
    private String namezh;

    /**
     * @return sys_role.id : 返回 
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id of sys_role : 设置 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sys_role.name : 返回 
     */
    public String getName() {
        return name;
    }

    /**
     * @param name of sys_role : 设置 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return sys_role.nameZh : 返回 角色名称
     */
    public String getNamezh() {
        return namezh;
    }

    /**
     * @param namezh of sys_role : 设置 角色名称
     */
    public void setNamezh(String namezh) {
        this.namezh = namezh == null ? null : namezh.trim();
    }
}