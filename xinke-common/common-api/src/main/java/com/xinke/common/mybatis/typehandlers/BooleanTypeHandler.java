package com.xinke.common.mybatis.typehandlers;

import com.xinke.common.constants.Symbol;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: BooleanTypeHandler
 * @Description: 自定义的mybatis的boolean类型转换器
 * @author sunjl
 */
public class BooleanTypeHandler extends BaseTypeHandler<Object> {
    @Override
    public void setParameter(final PreparedStatement ps, final int i, final Object parameter, final JdbcType jdbcType) throws SQLException {
        ps.setString(i, objectToBoolean(parameter) ? Symbol.TRUE_CHAR : Symbol.FALSE_CHAR);
    }

    @Override
    public Object getResult(final ResultSet resultset, final String columnName) throws SQLException {
        final Object s = resultset.getObject(columnName);
        return objectToBoolean(s);
    }

    @Override
    public Object getResult(final CallableStatement callablestatement, final int columnIndex) throws SQLException {
        final Object s = callablestatement.getObject(columnIndex);
        return objectToBoolean(s);
    }

    @Override
    public Object getNullableResult(final ResultSet resultset, final String columnName) throws SQLException {
        final Object s = resultset.getObject(columnName);
        return objectToBoolean(s);
    }

    @Override
    public Object getNullableResult(ResultSet resultset, int columnIndex) throws SQLException {
        final Object s = resultset.getObject(columnIndex);
        return objectToBoolean(s);
    }

    @Override
    public Object getNullableResult(final CallableStatement callablestatement, final int columnIndex) throws SQLException {
        final Object s = callablestatement.getObject(columnIndex);
        return objectToBoolean(s);
    }

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final Object parameter, final JdbcType jdbcType) throws SQLException {
        ps.setString(i, objectToBoolean(parameter) ? Symbol.TRUE_CHAR : Symbol.FALSE_CHAR);
    }

    /**
     * @Title: objectToBoolean
     * @Description: 将对象转化为Boolean
     * @param obj 对象
     * @return 对象转换后的bool型
     */
    private boolean objectToBoolean(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof Boolean) {
            return (Boolean) obj;
        } else {
            return Symbol.TRUE_CHAR.equals(obj);
        }
    }

}
