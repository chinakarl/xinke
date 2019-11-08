package com.xinke.management.dao;

import com.xinke.common.mybatis.Criteria;
import com.xinke.management.entity.beans.Category;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: CategoryMapper
 * @Description: category表对应的dao操作Mapper映射类
 * @author: Interests
 */
public interface CategoryMapper {
    /**
     * @Title CategoryMapper.countByExample
     * @Description: 根据查询条件，计算category个数
     * @param example 通用查询条件类
     * @return int 结果个数
     */
    int countByExample(Criteria example);

    /**
     * @Title CategoryMapper.deleteByExample
     * @Description: 根据查询条件，删除category
     * @param example 通用查询条件类
     * @return int  删除个数
     */
    int deleteByExample(Criteria example);

    /**
     * @Title CategoryMapper.deleteByPrimaryKey
     * @Description: 根据属性名称，删除category
     * @param id id
     * @return int  删除个数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Title CategoryMapper.insert
     * @Description: 插入一个category
     * @param record category的bean对象
     * @return int  插入个数
     */
    int insert(Category record);

    /**
     * @Title CategoryMapper.insertSelective
     * @Description: 插入一个只有部分字段的category
     * @param record 只含部分字段的category的bean对象
     * @return int  插入个数
     */
    int insertSelective(Category record);

    /**
     * @Title CategoryMapper.selectByExample
     * @Description: 根据查询条件类，返回category结果集
     * @param example 通用查询条件类
     * @return List<Category>category结果集
     */
    List<Category> selectByExample(Criteria example);

    /**
     * @Title CategoryMapper.selectByPrimaryKey
     * @Description: 根据主键类，返回category
     * @param id id
     * @return Category bean对象
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * @Title CategoryMapper.updateByExampleSelective
     * @Description: 根据查询条件更新category部分字段
     * @param record 要更新成为的Category对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExampleSelective(@Param("record") Category record, @Param("example") Criteria criteria);

    /**
     * @Title CategoryMapper.updateByExample
     * @Description: 根据查询条件更新category全表字段
     * @param record 要更新成为的Category对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExample(@Param("record") Category record, @Param("example") Criteria criteria);

    /**
     * @Title CategoryMapper.updateByPrimaryKeySelective
     * @Description: 根据主键更新category部分字段
     * @param record 要更新成为的Category对象
     * @return int 更新记录数
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * @Title CategoryMapper.updateByPrimaryKey
     * @Description: 根据主键更新category全部字段
     * @param record 要更新成为的Category对象
     * @return int 更新记录数
     */
    int updateByPrimaryKey(Category record);
}