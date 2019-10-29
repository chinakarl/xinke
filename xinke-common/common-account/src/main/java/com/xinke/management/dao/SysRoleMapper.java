package com.xinke.management.dao;

import com.xinke.management.entity.beans.SysRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.criteria.Criteria;

/**
 * @ClassName: SysRoleMapper
 * @Description: sys_role表对应的dao操作Mapper映射类
 * @author: Interests
 */
public interface SysRoleMapper {
    /**
     * @Title SysRoleMapper.countByExample
     * @Description: 根据查询条件，计算sys_role个数
     * @param example 通用查询条件类
     * @return int 结果个数
     */
    int countByExample(Criteria example);

    /**
     * @Title SysRoleMapper.deleteByExample
     * @Description: 根据查询条件，删除sys_role
     * @param example 通用查询条件类
     * @return int  删除个数
     */
    int deleteByExample(Criteria example);

    /**
     * @Title SysRoleMapper.deleteByPrimaryKey
     * @Description: 根据属性名称，删除sys_role
     * @param id id
     * @return int  删除个数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Title SysRoleMapper.insert
     * @Description: 插入一个sys_role
     * @param record sys_role的bean对象
     * @return int  插入个数
     */
    int insert(SysRole record);

    /**
     * @Title SysRoleMapper.insertSelective
     * @Description: 插入一个只有部分字段的sys_role
     * @param record 只含部分字段的sys_role的bean对象
     * @return int  插入个数
     */
    int insertSelective(SysRole record);

    /**
     * @Title SysRoleMapper.selectByExample
     * @Description: 根据查询条件类，返回sys_role结果集
     * @param example 通用查询条件类
     * @return List<SysRole>sys_role结果集
     */
    List<SysRole> selectByExample(Criteria example);

    /**
     * @Title SysRoleMapper.selectByPrimaryKey
     * @Description: 根据主键类，返回sys_role
     * @param id id
     * @return SysRole bean对象
     */
    SysRole selectByPrimaryKey(Integer id);

    /**
     * @Title SysRoleMapper.updateByExampleSelective
     * @Description: 根据查询条件更新sys_role部分字段
     * @param record 要更新成为的SysRole对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") Criteria criteria);

    /**
     * @Title SysRoleMapper.updateByExample
     * @Description: 根据查询条件更新sys_role全表字段
     * @param record 要更新成为的SysRole对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExample(@Param("record") SysRole record, @Param("example") Criteria criteria);

    /**
     * @Title SysRoleMapper.updateByPrimaryKeySelective
     * @Description: 根据主键更新sys_role部分字段
     * @param record 要更新成为的SysRole对象
     * @return int 更新记录数
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     * @Title SysRoleMapper.updateByPrimaryKey
     * @Description: 根据主键更新sys_role全部字段
     * @param record 要更新成为的SysRole对象
     * @return int 更新记录数
     */
    int updateByPrimaryKey(SysRole record);
}