package com.xinke.common.base;

/**
 * Created by cbb on 17/4/13.
 */
public abstract class BaseBean extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private SearchInfo search_;

    public SearchInfo getSearch_() {
        return search_;
    }

    public void setSearch_(SearchInfo search_) {
        this.search_ = search_;
    }

    public Integer totalCount() {
        return null == search_ ? null : search_.totalCount();
    }

    public void defaultOrderBy(String sortByName, String sortByOrder) {
        if(null == search_) search_ = new SearchInfo();
        search_.defaultOrderBy(sortByName, sortByOrder);
    }

    public void defaultOrderBy(String orderByClause) {
        if(null == search_) search_ = new SearchInfo();
        search_.defaultOrderBy(orderByClause);
    }
}
