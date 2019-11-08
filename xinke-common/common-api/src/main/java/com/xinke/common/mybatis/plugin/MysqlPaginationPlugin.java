package com.xinke.common.mybatis.plugin;

import com.xinke.common.page.Page;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * @ClassName: MysqlPaginationPlugin
 * @Description: mybatis的分页插件
 * @author sunjl
 */
@Intercepts({ @Signature(type = Executor.class, method = "query",
    args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class MysqlPaginationPlugin extends PaginationBasePlugin
{
    @Override
    protected String paginationSql(String sql, Page page) {
        StringBuilder pageSql = new StringBuilder();
        pageSql.append(sql);
        pageSql.append(" limit ").append(page.getBegin()).append(",").append(page.getLength());
        return pageSql.toString();
    }

    @Override
    protected String totalCountSql(String sql) {
        return "select count(1) from (" + sql + ") as total_count";
    }
}
