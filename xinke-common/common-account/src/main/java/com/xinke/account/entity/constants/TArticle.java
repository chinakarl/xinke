package com.xinke.account.entity.constants;

/**
 * @ClassName: TArticle
 * @Description: article表结构对应的常量类，定义字段名常量
 * @author: Interests
 */
public abstract class TArticle {
    /**
     * @Fields article.ID: 
     */
    public static final String ID = "id";

    /**
     * @Fields article.CONTENT: 文章内容
     */
    public static final String CONTENT = "content";

    /**
     * @Fields article.CREATE_TIME: 创建时间
     */
    public static final String CREATE_TIME = "create_time";

    /**
     * @Fields article.UPDATE_TIME: 更新时间
     */
    public static final String UPDATE_TIME = "update_time";

    /**
     * @Fields article.DELETE_STATUS: 是否有效  1.有效  2无效
     */
    public static final String DELETE_STATUS = "delete_status";
}