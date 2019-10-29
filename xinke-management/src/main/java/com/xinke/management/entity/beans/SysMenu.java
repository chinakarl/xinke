package com.xinke.management.entity.beans;

import org.mybatis.generator.base.BaseBean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: SysMenu
 * @Description: sys_menu表对应的java bean类
 * @author: Interests
 */
public class SysMenu extends BaseBean implements Serializable {
    /**
     * @Fields serialVersionUID : 自动生成默认序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields sys_menu.id :
     */
    private Integer id;

    /**
     * @Fields sys_menu.url :
     */
    private String url;

    /**
     * @Fields sys_menu.path :
     */
    private String path;

    /**
     * @Fields sys_menu.component :
     */
    private String component;

    /**
     * @Fields sys_menu.name :
     */
    private String name;

    /**
     * @Fields sys_menu.iconCls :
     */
    private String iconcls;

    /**
     * @Fields sys_menu.keepAlive :
     */
    private Boolean keepalive;

    /**
     * @Fields sys_menu.requireAuth :
     */
    private Boolean requireauth;

    /**
     * @Fields sys_menu.parentId :
     */
    private Integer parentid;

    /**
     * @Fields sys_menu.enabled :
     */
    private Boolean enabled;

    /**
     * @Fields sys_menu.create_time :
     */
    private Date createTime;

    /**
     * @Fields sys_menu.create_no :
     */
    private Integer createNo;

    /**
     * @Fields sys_menu.update_time :
     */
    private Date updateTime;

    /**
     * @Fields sys_menu.update_no :
     */
    private Integer updateNo;

    private List<SysRole> roles;
    private List<SysMenu> children;
    private MenuMeta meta;

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    public MenuMeta getMeta() {
        return meta;
    }

    public void setMeta(MenuMeta meta) {
        this.meta = meta;
    }

    /**
     * @return sys_menu.id : 返回 
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id of sys_menu : 设置 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sys_menu.url : 返回 
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url of sys_menu : 设置 
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return sys_menu.path : 返回 
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path of sys_menu : 设置 
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * @return sys_menu.component : 返回 
     */
    public String getComponent() {
        return component;
    }

    /**
     * @param component of sys_menu : 设置 
     */
    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    /**
     * @return sys_menu.name : 返回 
     */
    public String getName() {
        return name;
    }

    /**
     * @param name of sys_menu : 设置 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return sys_menu.iconCls : 返回 
     */
    public String getIconcls() {
        return iconcls;
    }

    /**
     * @param iconcls of sys_menu : 设置 
     */
    public void setIconcls(String iconcls) {
        this.iconcls = iconcls == null ? null : iconcls.trim();
    }

    /**
     * @return sys_menu.keepAlive : 返回 
     */
    public Boolean getKeepalive() {
        return keepalive;
    }

    /**
     * @param keepalive of sys_menu : 设置 
     */
    public void setKeepalive(Boolean keepalive) {
        this.keepalive = keepalive;
    }

    /**
     * @return sys_menu.requireAuth : 返回 
     */
    public Boolean getRequireauth() {
        return requireauth;
    }

    /**
     * @param requireauth of sys_menu : 设置 
     */
    public void setRequireauth(Boolean requireauth) {
        this.requireauth = requireauth;
    }

    /**
     * @return sys_menu.parentId : 返回 
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * @param parentid of sys_menu : 设置 
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * @return sys_menu.enabled : 返回 
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled of sys_menu : 设置 
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return sys_menu.create_time : 返回 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime of sys_menu : 设置 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return sys_menu.create_no : 返回 
     */
    public Integer getCreateNo() {
        return createNo;
    }

    /**
     * @param createNo of sys_menu : 设置 
     */
    public void setCreateNo(Integer createNo) {
        this.createNo = createNo;
    }

    /**
     * @return sys_menu.update_time : 返回 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime of sys_menu : 设置 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return sys_menu.update_no : 返回 
     */
    public Integer getUpdateNo() {
        return updateNo;
    }

    /**
     * @param updateNo of sys_menu : 设置 
     */
    public void setUpdateNo(Integer updateNo) {
        this.updateNo = updateNo;
    }
}