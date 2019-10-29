package com.xinke.common.mybatis.typehandlers;

import com.xinke.common.utils.DateUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @ClassName: StringTypeHandler
 * @Description: 自定义mybatis的jdbc转String处理类
 * @author sunjl
 */
public class StringTypeHandler extends BaseTypeHandler<Object> {

    @Override
    public void setParameter(final PreparedStatement ps, final int i, final Object parameter, final JdbcType jdbcType) throws SQLException {
        ps.setString(i, objectToString(parameter));
    }

    @Override
    public Object getResult(final ResultSet resultset, final String columnName) throws SQLException {
        final Object s = resultset.getObject(columnName);
        return objectToString(s);
    }

    @Override
    public Object getResult(final CallableStatement callablestatement, final int columnIndex) throws SQLException {
        final Object s = callablestatement.getObject(columnIndex);
        return objectToString(s);
    }

    @Override
    public Object getNullableResult(final ResultSet resultset, final String columnName) throws SQLException {
        final Object s = resultset.getObject(columnName);
        return objectToString(s);
    }

    @Override
    public Object getNullableResult(ResultSet resultset, int columnIndex) throws SQLException {
        final Object s = resultset.getObject(columnIndex);
        return objectToString(s);
    }

    @Override
    public Object getNullableResult(final CallableStatement callablestatement, final int columnIndex) throws SQLException {
        final Object s = callablestatement.getObject(columnIndex);
        return objectToString(s);
    }

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final Object parameter, final JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    /**
     * @Title: objectToString
     * @Description: 将对象转化为String
     * @param obj 对象
     * @return 对象转换后的字符串
     */
    private String objectToString(Object obj) {
        if (obj == null) {
            return "";
        } else if (obj instanceof Date) {
            return DateUtil.format((Date) obj);
        } else {
            return obj.toString();
        }
    }
}
