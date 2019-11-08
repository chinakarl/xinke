package com.xinke.management.dao;

import com.xinke.common.mybatis.Criteria;
import com.xinke.management.entity.beans.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: HrMapper
 * @Description: hr表对应的dao操作Mapper映射类
 * @author: Interests
 */
public interface SysUserMapper {
    /**
     * @Title HrMapper.countByExample
     * @Description: 根据查询条件，计算hr个数
     * @param example 通用查询条件类
     * @return int 结果个数
     */
    int countByExample(Criteria example);

    /**
     * @Title HrMapper.deleteByExample
     * @Description: 根据查询条件，删除hr
     * @param example 通用查询条件类
     * @return int  删除个数
     */
    int deleteByExample(Criteria example);

    /**
     * @Title HrMapper.deleteByPrimaryKey
     * @Description: 根据属性名称，删除hr
     * @param id id
     * @return int  删除个数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Title HrMapper.insert
     * @Description: 插入一个hr
     * @param record hr的bean对象
     * @return int  插入个数
     */
    int insert(SysUser record);

    /**
     * @Title HrMapper.insertSelective
     * @Description: 插入一个只有部分字段的hr
     * @param record 只含部分字段的hr的bean对象
     * @return int  插入个数
     */
    int insertSelective(SysUser record);

    /**
     * @Title HrMapper.selectByExample
     * @Description: 根据查询条件类，返回hr结果集
     * @param example 通用查询条件类
     * @return List<Hr>hr结果集
     */
    List<SysUser> selectByExample(Criteria example);

    /**
     * @Title HrMapper.selectByPrimaryKey
     * @Description: 根据主键类，返回hr
     * @param id id
     * @return Hr bean对象
     */
    SysUser selectByPrimaryKey(Integer id);

    /**
     * @Title HrMapper.updateByExampleSelective
     * @Description: 根据查询条件更新hr部分字段
     * @param record 要更新成为的Hr对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") Criteria criteria);

    /**
     * @Title HrMapper.updateByExample
     * @Description: 根据查询条件更新hr全表字段
     * @param record 要更新成为的Hr对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExample(@Param("record") SysUser record, @Param("example") Criteria criteria);

    /**
     * @Title HrMapper.updateByPrimaryKeySelective
     * @Description: 根据主键更新hr部分字段
     * @param record 要更新成为的Hr对象
     * @return int 更新记录数
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * @Title HrMapper.updateByPrimaryKey
     * @Description: 根据主键更新hr全部字段
     * @param record 要更新成为的Hr对象
     * @return int 更新记录数
     */
    int updateByPrimaryKey(SysUser record);

    SysUser selectUserByName(String name);

    List<SysUser> getUsersByKeywords(String keywords);
}