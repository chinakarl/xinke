package com.xinke.account.entity.beans;

import java.io.Serializable;
import java.util.Date;
import org.mybatis.generator.base.BaseBean;

/**
 * @ClassName: Article
 * @Description: article表对应的java bean类
 * @author: Interests
 */
public class Article extends BaseBean implements Serializable {
    /**
     * @Fields serialVersionUID : 自动生成默认序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields article.id :
     */
    private Integer id;

    /**
     * @Fields article.content :文章内容
     */
    private String content;

    /**
     * @Fields article.create_time :创建时间
     */
    private Date createTime;

    /**
     * @Fields article.update_time :更新时间
     */
    private Date updateTime;

    /**
     * @Fields article.delete_status :是否有效  1.有效  2无效
     */
    private String deleteStatus;

    /**
     * @return article.id : 返回 
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id of article : 设置 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return article.content : 返回 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content of article : 设置 文章内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @return article.create_time : 返回 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime of article : 设置 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return article.update_time : 返回 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime of article : 设置 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return article.delete_status : 返回 是否有效  1.有效  2无效
     */
    public String getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * @param deleteStatus of article : 设置 是否有效  1.有效  2无效
     */
    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus == null ? null : deleteStatus.trim();
    }
}