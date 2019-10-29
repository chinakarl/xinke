package com.xinke.common.mybatis.plugin;

import com.ai.market.common.page.Page;
import com.ai.market.common.page.PageCondition;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName: PaginationBasePlugin
 * @Description: 分页插件
 * @author sunjl
 */
@Intercepts({ @Signature(type = Executor.class, method = "query",
    args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public abstract class PaginationBasePlugin implements Interceptor {

    /**
     * @Fields INDEX_MAPPED_STATEMENT : mappStatement参数位置
     */
    static final int INDEX_MAPPED_STATEMENT = 0;
    /**
     * @Fields INDEX_PARAMETER : 入参对象参数位置
     */
    static final int INDEX_PARAMETER = 1;
    /**
     * @Fields INDEX_ROW_BOUNDS : 记录获取数参数位置
     */
    static final int INDEX_ROW_BOUNDS = 2;
    /**
     * @Fields INDEX_RESULT_HANDLER : 结果集处理参数位置
     */
    static final int INDEX_RESULT_HANDLER = 3;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object intercept(final Invocation invocation) throws Throwable {
        final MappedStatement mappedStatement = this.getMappedStatement(invocation);
        final Object parameter = this.getParameter(invocation);
        final BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        // Object[] queryArgs = invocation.getArgs();
        // MappedStatement ms = (MappedStatement) queryArgs[0];
        // BoundSql boundSql = ms.getBoundSql(queryArgs[1]);
        String sql = boundSql.getSql().trim();
        // Object args = queryArgs[1];

        // 分析是否含有分页参数，如果没有则不是分页查询
        // 注意：在多参数的情况下，只处理第一个分页参数
        PageCondition condition = null;
        if (parameter instanceof PageCondition) { // 只有一个参数的情况
            condition = (PageCondition) parameter;
        } else if (parameter instanceof Map) { // 多参数的情况，找到第一个Criteria的参数
            for (final Map.Entry<Object, Object> e : ((Map<Object, Object>) parameter).entrySet()) {
                if (e.getValue() instanceof PageCondition) {
                    condition = (PageCondition) e.getValue();
                    break;
                }
            }
        }
        // 如果基本查询条件为null，则略过继续执行
        if (condition == null) {
            return invocation.proceed();
        }

        final Page page = condition.getPage();
        // 如果分页参数不为空，则需要计算记录总数
        if (page != null) {
            if (page.isEnableCount() || page.isOnlyCount()) {// 如果启用了获取count，则计算记录总数
                final Connection connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
                final int totalCount = getTotalCount(sql, connection, mappedStatement, parameter, boundSql);
                // 关闭连接。。。
                connection.close();
                page.setCount(totalCount);
            }
            if (page.isOnlyCount()) {// 若是只需要计算记录总数，则不需要再获取结果，直接返回空记录数
                // sql = paginationSql(sql, new Page(0, 0)); // 通过子类实现
                // this.setMappedStatement(invocation, this.buildMappedStatement(mappedStatement, boundSql, sql));
                // return invocation.proceed();
                return new ArrayList();
            }
        }

        // 如果order by参数不为空，则需要排序后，再分页
        sql = orderbySql(sql, condition.getOrderByClause());

        // 如果page不为空，则生成分页sql
        if (page != null) {
            sql = paginationSql(sql, page); // 通过子类实现
        }
        this.setMappedStatement(invocation, this.buildMappedStatement(mappedStatement, boundSql, sql));
        this.setRowBounds(invocation, RowBounds.DEFAULT);
        return invocation.proceed();
    }

    /**
     * 计算sql返回的记录总数
     * @param sql 要计算的sql
     * @param connection 数据库连接
     * @param statement mybatis语句节点
     * @param parameterObj sql的入参
     * @param boundSql 动态sql
     * @return sql执行的结果总数
     * @throws SQLException sql异常
     */
    public int getTotalCount(final String sql, final Connection connection, final MappedStatement statement, final Object parameterObj,
        final BoundSql boundSql) throws SQLException {

        final String totalCountSql = totalCountSql(sql);

        PreparedStatement pStatement = null;
        ResultSet rs = null;

        try {
            pStatement = connection.prepareStatement(totalCountSql);

            // BoundSql bSql = new BoundSql(statement.getConfiguration(), totalCountSql, boundSql.getParameterMappings(), parameterObj);

            setParameters(pStatement, this.buildMappedStatement(statement, boundSql, totalCountSql), boundSql, parameterObj);

            rs = pStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (final SQLException e) {
            throw e;
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                    rs = null;
                }
                if (null != pStatement) {
                    pStatement.close();
                    pStatement = null;
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    /**
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
     * @param ps mybatis执行准备
     * @param mappedStatement mybatis语句节点
     * @param boundSql 动态sql
     * @param parameterObject 入参
     * @throws SQLException sql异常
     */
    @SuppressWarnings("unchecked")
    private void setParameters(final PreparedStatement ps, final MappedStatement mappedStatement, final BoundSql boundSql,
        final Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        final List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            final Configuration configuration = mappedStatement.getConfiguration();
            final TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            final MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i = i + 1) {
                final ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    final String propertyName = parameterMapping.getProperty();
                    final PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    @SuppressWarnings("rawtypes")
                    final TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement "
                            + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    /**
     * @Title: paginationSql
     * @Description: 子类实现分页sql处理
     * @param sql 原始sql
     * @param page 分页参数
     * @return 分页处理后的sql
     */
    protected abstract String paginationSql(String sql, Page page); // 子类实现

    /**
     * @Title: totalCountSql
     * @Description: 子类实现总数统计sql处理
     * @param sql 原始sql
     * @return 分页处理后的sql
     */
    protected abstract String totalCountSql(String sql); // 子类实现

    /**
     * @Title: buildMappedStatement
     * @Description: 生成新的mappedStatement
     * @param ms 原始ms
     * @param boundSql 原始boundsql
     * @param sql 分页最终sql
     * @return 新的mappedStatement
     */
    private MappedStatement buildMappedStatement(final MappedStatement ms, final BoundSql boundSql, final String sql) {
        final Builder builder = new Builder(ms.getConfiguration(), ms.getId(), new BoundSqlSqlSource(ms, boundSql, sql),
            ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.fetchSize(ms.getFetchSize());
        builder.timeout(ms.getTimeout());
        builder.statementType(ms.getStatementType());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.keyProperty(delimitedArraytoString(ms.getKeyProperties()));
        builder.keyColumn(delimitedArraytoString(ms.getKeyColumns()));
        builder.databaseId(ms.getDatabaseId());

        return builder.build();
    }

    /**
     * @Title: getMappedStatement
     * @Description: 获取 MappedStatement
     * @param invocation 调用
     * @return MappedStatement
     */
    private MappedStatement getMappedStatement(final Invocation invocation) {
        return (MappedStatement) invocation.getArgs()[INDEX_MAPPED_STATEMENT];
    }

    /**
     * @Title: setMappedStatement
     * @Description: 设置MappedStatement
     * @param invocation 调用
     * @param mappedStatement 新的MappedStatement
     */
    private void setMappedStatement(final Invocation invocation, final MappedStatement mappedStatement) {
        invocation.getArgs()[INDEX_MAPPED_STATEMENT] = mappedStatement;
    }

    /**
     * @Title: getParameter
     * @Description: 获取sql入参
     * @param invocation 调用
     * @return sql入参
     */
    private Object getParameter(final Invocation invocation) {
        return invocation.getArgs()[INDEX_PARAMETER];
    }

    /**
     * @Title: getRowBounds
     * @Description: 获取行数
     * @param invocation 调用
     * @return 行数范围
     */
    @SuppressWarnings("unused")
    private RowBounds getRowBounds(final Invocation invocation) {
        return (RowBounds) invocation.getArgs()[INDEX_ROW_BOUNDS];
    }

    /**
     * @Title: setRowBounds
     * @Description: 设置行数范围
     * @param invocation 调用
     * @param rowBounds 行数范围
     */
    private void setRowBounds(final Invocation invocation, final RowBounds rowBounds) {
        invocation.getArgs()[INDEX_ROW_BOUNDS] = rowBounds;
    }

    @Override
    public Object plugin(final Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * @Title: delimitedArraytoString
     * @Description: 将参数转string
     * @param in properties
     * @return keyProperty
     */
    private static String delimitedArraytoString(final String[] in) {
        if ((in == null) || (in.length == 0)) {
            return null;
        } else {
            final StringBuffer answer = new StringBuffer();
            for (final String str : in) {
                answer.append(str).append(",");
            }
            return answer.toString();
        }
    }

    /**
     * @ClassName: BoundSqlSqlSource
     * @Description: boundsql处理类
     * @author sunjl
     */
    public static class BoundSqlSqlSource implements SqlSource {

        /**
         * @Fields boundSql : 新boundsql
         */
        private final BoundSql boundSql;

        /**
         * <p>
         * Title: 构造函数
         * </p>
         * <p>
         * Description:根据新的ms和boundsql生成sqlsource
         * </p>
         * @param ms 新的ms
         * @param boundSql 新的boundsql
         * @param sql 执行sql
         */
        public BoundSqlSqlSource(final MappedStatement ms, final BoundSql boundSql, final String sql) {
            this.boundSql = buildBoundSql(ms, boundSql, sql);
        }

        @Override
        public BoundSql getBoundSql(final Object parameterObject) {
            return boundSql;
        }

        /**
         * @Title: buildBoundSql
         * @Description: 入参处理
         * @param ms 新的ms
         * @param boundSql 新的boundsql
         * @param sql 执行sql
         * @return 最终boundsql
         */
        private BoundSql buildBoundSql(final MappedStatement ms, final BoundSql boundSql, final String sql) {
            final BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            for (final ParameterMapping mapping : boundSql.getParameterMappings()) {
                final String prop = mapping.getProperty();
                if (boundSql.hasAdditionalParameter(prop)) {
                    newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
                }
            }
            return newBoundSql;
        }
    }

    /**
     * @Title: orderbySql
     * @Description: 子类实现排序sql处理
     * @param sql 原始sql
     * @param orderByClause 排序参数
     * @return 排序处理后的sql
     */
    protected String orderbySql(final String sql, final String orderByClause) {
        if (StringUtils.isNotBlank(orderByClause)) {
            return sql + " order by " + orderByClause;
        } else {
            return sql;
        }
    }

    @Override
    public void setProperties(final Properties properties) {
        // String dialectClass = properties.getProperty("dialect");
        // try {
        // dialect = (Dialect) Class.forName(dialectClass).newInstance();
        // } catch (Exception e) {
        // throw new MyBatisShardsException("Can not create dialect instance by dialect:"
        // + dialectClass, e);
        // }
    }
}
