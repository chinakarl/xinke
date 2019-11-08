package com.xinke.common.base;

import com.xinke.common.mybatis.Criteria;
import com.xinke.common.page.Page;
import org.apache.commons.lang3.StringUtils;

/**
 * @author cbb
 * @ClassName: SearchHelper
 * @Description:查询工具类
 */
public class SearchHelper
{

    public static Page getPage(final int page, final int pageCount, boolean enableCount)
    {
        if (pageCount <= 0 || page < 1)
            return null;
        Page newPage = new Page((page - 1) * pageCount, pageCount);
        newPage.setEnableCount(enableCount);
        return newPage;
    }

    public static Page getNumPage(final int start, final int pageCount, boolean enableCount)
    {
        if (pageCount <= 0 || start <= 0)
            return null;
        Page newPage = new Page(start, pageCount);
        newPage.setEnableCount(enableCount);
        return newPage;
    }

    public static Page onlyCountPage()
    {
        Page newPage = new Page();
        newPage.setOnlyCount(true);
        return newPage;
    }

    /**
     * 从entity获取Page
     *
     * @param entity
     * @return
     * @Title: get
     * @Description:
     */
    public static Page getPage(BaseBean entity)
    {
        if (null == entity)
            return null;
        SearchInfo searchInfo = entity.getSearch_();
        if (null == searchInfo)
            return null;
        Page page = null;
        if (searchInfo.isOnlyCount())
        {
            page = SearchHelper.onlyCountPage();
        }
        if (searchInfo.getPageCount() > 0)
        {
            if (null == searchInfo.getPage() && null == searchInfo.getStart())
            {
                searchInfo.setPage(0);
            }
            if (null != searchInfo.getPage())
            {
                page =
                    SearchHelper.getPage(searchInfo.getPage(), searchInfo.getPageCount(), searchInfo.isEnableCount());
            }
            else if (null != searchInfo.getStart())
            {
                page = SearchHelper.getNumPage(searchInfo.getStart(),
                    searchInfo.getPageCount(),
                    searchInfo.isEnableCount());
            }
        }
        searchInfo.bindPage(page);
        return page;
    }

    /**
     * 从entity设置order by
     *
     * @param criteria
     * @param entity
     * @Title: setSord
     * @Description:
     */
    public static void setOrderBy(Criteria criteria, BaseBean entity)
    {
        if (null == entity)
            return;
        SearchInfo searchInfo = entity.getSearch_();
        if (null == searchInfo)
            return;
        if (StringUtils.isNotBlank(searchInfo.getOrderByClause()))
        {
            criteria.setOrderByClause(searchInfo.getOrderByClause());
        }
        else if (StringUtils.isNotBlank(searchInfo.getSortByName()) &&
            StringUtils.isNotBlank(searchInfo.getSortByOrder()))
        {
            criteria.setOrderByClause(searchInfo.getSortByName() + " " + searchInfo.getSortByOrder());
        }
    }

    /**
     * 从entity设置Criteria
     *
     * @param entity
     * @return
     */
    public static Criteria getCriteria(BaseBean entity)
    {
        Page page = SearchHelper.getPage(entity);
        Criteria example = new Criteria(page);
        SearchHelper.setOrderBy(example, entity);
        return example;
    }

}
