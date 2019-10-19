package com.xinke.account.entity.dao;

import com.xinke.account.entity.beans.Article;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.criteria.Criteria;

/**
 * @ClassName: ArticleMapper
 * @Description: article表对应的dao操作Mapper映射类
 * @author: Interests
 */
public interface ArticleMapper {
    /**
     * @Title ArticleMapper.countByExample
     * @Description: 根据查询条件，计算article个数
     * @param example 通用查询条件类
     * @return int 结果个数
     */
    int countByExample(Criteria example);

    /**
     * @Title ArticleMapper.deleteByExample
     * @Description: 根据查询条件，删除article
     * @param example 通用查询条件类
     * @return int  删除个数
     */
    int deleteByExample(Criteria example);

    /**
     * @Title ArticleMapper.deleteByPrimaryKey
     * @Description: 根据属性名称，删除article
     * @param id id
     * @return int  删除个数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Title ArticleMapper.insert
     * @Description: 插入一个article
     * @param record article的bean对象
     * @return int  插入个数
     */
    int insert(Article record);

    /**
     * @Title ArticleMapper.insertSelective
     * @Description: 插入一个只有部分字段的article
     * @param record 只含部分字段的article的bean对象
     * @return int  插入个数
     */
    int insertSelective(Article record);

    /**
     * @Title ArticleMapper.selectByExample
     * @Description: 根据查询条件类，返回article结果集
     * @param example 通用查询条件类
     * @return List<Article>article结果集
     */
    List<Article> selectByExample(Criteria example);

    /**
     * @Title ArticleMapper.selectByPrimaryKey
     * @Description: 根据主键类，返回article
     * @param id id
     * @return Article bean对象
     */
    Article selectByPrimaryKey(Integer id);

    /**
     * @Title ArticleMapper.updateByExampleSelective
     * @Description: 根据查询条件更新article部分字段
     * @param record 要更新成为的Article对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExampleSelective(@Param("record") Article record, @Param("example") Criteria criteria);

    /**
     * @Title ArticleMapper.updateByExample
     * @Description: 根据查询条件更新article全表字段
     * @param record 要更新成为的Article对象
     * @param criteria 更新记录的查询条件
     * @return int 更新记录数
     */
    int updateByExample(@Param("record") Article record, @Param("example") Criteria criteria);

    /**
     * @Title ArticleMapper.updateByPrimaryKeySelective
     * @Description: 根据主键更新article部分字段
     * @param record 要更新成为的Article对象
     * @return int 更新记录数
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * @Title ArticleMapper.updateByPrimaryKey
     * @Description: 根据主键更新article全部字段
     * @param record 要更新成为的Article对象
     * @return int 更新记录数
     */
    int updateByPrimaryKey(Article record);
}