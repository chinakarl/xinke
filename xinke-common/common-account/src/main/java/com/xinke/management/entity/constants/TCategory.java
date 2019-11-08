package com.xinke.management.entity.constants;

/**
 * @ClassName: TCategory
 * @Description: category表结构对应的常量类，定义字段名常量
 * @author: Interests
 */
public abstract class TCategory {
    /**
     * @Fields category.ID: 主键
     */
    public static final String ID = "id";

    /**
     * @Fields category.CATEGORY_NAME: 类别的名称
     */
    public static final String CATEGORY_NAME = "category_name";

    /**
     * @Fields category.CATEGORY_CODE: 类别编码
     */
    public static final String CATEGORY_CODE = "category_code";

    /**
     * @Fields category.PARENT_ID: 父级id，顶级默认为0
     */
    public static final String PARENT_ID = "parent_id";

    /**
     * @Fields category.HIERARCHY: 层级，从1开始，如果父类层级为1，子类就为2
     */
    public static final String HIERARCHY = "hierarchy";

    /**
     * @Fields category.CREATE_TIME: 创建时间
     */
    public static final String CREATE_TIME = "create_time";

    /**
     * @Fields category.UPDATE_TIME: 更新时间
     */
    public static final String UPDATE_TIME = "update_time";

    /**
     * @Fields category.CREATE_NO: 创建人号
     */
    public static final String CREATE_NO = "create_no";

    /**
     * @Fields category.UPDATE_NO: 更新人
     */
    public static final String UPDATE_NO = "update_no";
}