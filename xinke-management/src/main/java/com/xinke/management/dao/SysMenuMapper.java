package com.xinke.management.dao;

import com.xinke.management.entity.beans.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.criteria.Criteria;

import java.util.List;

/**
 * @ClassName: SysMenuMapper
 * @Description: sys_menu表对应的dao操作Mapper映射类
 * @author: Interests
 */
public interface SysMenuMapper {
    /**
     * @Title SysMenuMapper.countByExample
     * @Description: 根据查询条件，计算sys_menu个数
     * @param example 通用查询条件类
     * @return int 结果个数
     */
    int countByExample(Criteria example);

    /**
     * @Title SysMenuMapper.deleteByExample
     * @Description: 根据查询条件，删除sys_menu
     * @param example 通用查询条件类
     * @return int  删除个数
     */
    int deleteByExample(Criteria example);

    /**
     * @Title SysMenuMapper.deleteByPrimaryKey
     * @Description: 根据属性名称，删除sys_menu
     * @param id id
     * @return int  删除个数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Title SysMenuMapper.insert
     * @Description: 插入一个sys_menu
     * @param record sys_menu的bean对象
     * @return int  插入个数
     */
    int insert(SysMenu record);

    /**
     * @Title SysMenuMapper.insertSelective
     * @Description: 插入一个只有部分字段的sys_menu
     * @param record 只含部分字段的sys_menu的bean对象
     * @return int  插入个数
     */
    int insertSelective(SysMenu record);

    /**
     * @Title SysMenuMapper.selectByExample
     * @Description: 根据查询条件类，返回sys_menu结果集
     * @param example 通用查询条件类
     * @return List<SysMenu>sys_menu结果集
     */
    List<SysMenu> selectByExample(Criteria example);

    /**
     * @Title SysMenuMapper.selectByPrimaryKey
     * @Description: 根据主键类，返回sys_menu
     * @param id id
     * @return SysMenu bean对象
     */
    SysMenu selectByPrimaryKey(Integer id);

    /**
     * @Title SysMenuMapper.updateByExampleSelective
     * @Description: 根据查询条件更新sys_menu部分字段
     * @param record 要更新成为的SysMenu对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") Criteria criteria);

    /**
     * @Title SysMenuMapper.updateByExample
     * @Description: 根据查询条件更新sys_menu全表字段
     * @param record 要更新成为的SysMenu对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExample(@Param("record") SysMenu record, @Param("example") Criteria criteria);

    /**
     * @Title SysMenuMapper.updateByPrimaryKeySelective
     * @Description: 根据主键更新sys_menu部分字段
     * @param record 要更新成为的SysMenu对象
     * @return int 更新记录数
     */
    int updateByPrimaryKeySelective(SysMenu record);

    /**
     * @Title SysMenuMapper.updateByPrimaryKey
     * @Description: 根据主键更新sys_menu全部字段
     * @param record 要更新成为的SysMenu对象
     * @return int 更新记录数
     */
    int updateByPrimaryKey(SysMenu record);


    List<SysMenu> getMenusByUserId(Long id);
}