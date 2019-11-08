package com.xinke.management.dao;

import com.xinke.management.entity.beans.SysUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.criteria.Criteria;

/**
 * @ClassName: SysUserMapper
 * @Description: sys_user表对应的dao操作Mapper映射类
 * @author: Interests
 */
public interface SysUserMapper {
    /**
     * @Title SysUserMapper.countByExample
     * @Description: 根据查询条件，计算sys_user个数
     * @param example 通用查询条件类
     * @return int 结果个数
     */
    int countByExample(Criteria example);

    /**
     * @Title SysUserMapper.deleteByExample
     * @Description: 根据查询条件，删除sys_user
     * @param example 通用查询条件类
     * @return int  删除个数
     */
    int deleteByExample(Criteria example);

    /**
     * @Title SysUserMapper.deleteByPrimaryKey
     * @Description: 根据属性名称，删除sys_user
     * @param id id
     * @return int  删除个数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Title SysUserMapper.insert
     * @Description: 插入一个sys_user
     * @param record sys_user的bean对象
     * @return int  插入个数
     */
    int insert(SysUser record);

    /**
     * @Title SysUserMapper.insertSelective
     * @Description: 插入一个只有部分字段的sys_user
     * @param record 只含部分字段的sys_user的bean对象
     * @return int  插入个数
     */
    int insertSelective(SysUser record);

    /**
     * @Title SysUserMapper.selectByExample
     * @Description: 根据查询条件类，返回sys_user结果集
     * @param example 通用查询条件类
     * @return List<SysUser>sys_user结果集
     */
    List<SysUser> selectByExample(Criteria example);

    /**
     * @Title SysUserMapper.selectByPrimaryKey
     * @Description: 根据主键类，返回sys_user
     * @param id id
     * @return SysUser bean对象
     */
    SysUser selectByPrimaryKey(Integer id);

    /**
     * @Title SysUserMapper.updateByExampleSelective
     * @Description: 根据查询条件更新sys_user部分字段
     * @param record 要更新成为的SysUser对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") Criteria criteria);

    /**
     * @Title SysUserMapper.updateByExample
     * @Description: 根据查询条件更新sys_user全表字段
     * @param record 要更新成为的SysUser对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExample(@Param("record") SysUser record, @Param("example") Criteria criteria);

    /**
     * @Title SysUserMapper.updateByPrimaryKeySelective
     * @Description: 根据主键更新sys_user部分字段
     * @param record 要更新成为的SysUser对象
     * @return int 更新记录数
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * @Title SysUserMapper.updateByPrimaryKey
     * @Description: 根据主键更新sys_user全部字段
     * @param record 要更新成为的SysUser对象
     * @return int 更新记录数
     */
    int updateByPrimaryKey(SysUser record);
}