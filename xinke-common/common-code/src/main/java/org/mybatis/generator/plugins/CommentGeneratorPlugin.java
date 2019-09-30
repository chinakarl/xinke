package org.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;

public class CommentGeneratorPlugin extends PluginAdapter {

    /**
     *
     */
    public CommentGeneratorPlugin() {
        super();
    }

    @Override
    public boolean validate(final List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();

        topLevelClass.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @ClassName: "); //$NON-NLS-1$
        sb.append(topLevelClass.getType().getShortName());
        topLevelClass.addJavaDocLine(sb.toString()); //$NON-NLS-1$

        sb = new StringBuilder();
        sb.append(" * @Description: ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append("表对应的java bean类");
        topLevelClass.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @author: Interests");
        topLevelClass.addJavaDocLine(sb.toString());

        topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$

        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();

        topLevelClass.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @ClassName: "); //$NON-NLS-1$
        sb.append(topLevelClass.getType().getShortName());
        topLevelClass.addJavaDocLine(sb.toString()); //$NON-NLS-1$

        sb = new StringBuilder();
        sb.append(" * @Description: ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append("表对应的java bean主键类");
        topLevelClass.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @author: Interests");
        topLevelClass.addJavaDocLine(sb.toString());

        topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$

        return true;
    }

    @Override
    public boolean clientGenerated(final Interface interfaze, final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();

        interfaze.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @ClassName: "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        interfaze.addJavaDocLine(sb.toString()); //$NON-NLS-1$

        sb = new StringBuilder();
        sb.append(" * @Description: ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append("表对应的dao操作Mapper映射类");
        interfaze.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @author: Interests");
        interfaze.addJavaDocLine(sb.toString());

        interfaze.addJavaDocLine(" */"); //$NON-NLS-1$

        return true;
    }

    @Override
    public boolean modelFieldGenerated(final Field field, final TopLevelClass topLevelClass, final IntrospectedColumn introspectedColumn,
        final IntrospectedTable introspectedTable, final ModelClassType modelClassType) {

        final StringBuilder sb = new StringBuilder();

        field.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Fields "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        sb.append(" :");
        sb.append(introspectedColumn.getRemarks());
        field.addJavaDocLine(sb.toString());

        field.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean modelGetterMethodGenerated(final Method method, final TopLevelClass topLevelClass, final IntrospectedColumn introspectedColumn,
        final IntrospectedTable introspectedTable, final ModelClassType modelClassType) {
        final StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @return "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        sb.append(" : 返回 ");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(final Method method, final TopLevelClass topLevelClass, final IntrospectedColumn introspectedColumn,
        final IntrospectedTable introspectedTable, final ModelClassType modelClassType) {
        final StringBuilder sb = new StringBuilder();

        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @param "); //$NON-NLS-1$
        sb.append(method.getParameters().get(0).getName());
        sb.append(" of "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append(" : 设置 ");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(final Method method, final TopLevelClass interfaze, final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        // sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据查询条件，计算" + introspectedTable.getFullyQualifiedTable() + "个数");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param example 通用查询条件类");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int 结果个数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(final Method method, final TopLevelClass interfaze, final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据查询条件，删除" + introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param example 通用查询条件类");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int  删除个数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(final Method method, final TopLevelClass interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据属性名称，删除" + introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());
        final List<Parameter> params = method.getParameters();
        for (int i = 0; i < params.size(); i++) {
            final Parameter p = params.get(i);
            sb = new StringBuilder();
            sb.append(" * @param " + p.getName() + " " + p.getName());
            method.addJavaDocLine(sb.toString());
        }

        sb = new StringBuilder();
        sb.append(" * @return int  删除个数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientInsertMethodGenerated(final Method method, final TopLevelClass interfaze, final IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 插入一个" + introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param record " + introspectedTable.getFullyQualifiedTable() + "的bean对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int  插入个数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(final Method method, final TopLevelClass interfaze, final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 插入一个只有部分字段的" + introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param record 只含部分字段的" + introspectedTable.getFullyQualifiedTable() + "的bean对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int  插入个数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(final Method method, final TopLevelClass interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        // sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据查询条件类，返回" + introspectedTable.getFullyQualifiedTable() + "结果集");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param example 通用查询条件类");
        method.addJavaDocLine(sb.toString());

        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");
        sb = new StringBuilder();
        sb.append(" * @return List<" + tableName + ">" + introspectedTable.getFullyQualifiedTable() + "结果集");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(final Method method, final TopLevelClass interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据主键类，返回" + introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());
        final List<Parameter> params = method.getParameters();
        for (int i = 0; i < params.size(); i++) {
            final Parameter p = params.get(i);
            sb = new StringBuilder();
            sb.append(" * @param " + p.getName() + " " + p.getName());
            method.addJavaDocLine(sb.toString());
        }
        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");

        sb = new StringBuilder();
        sb.append(" * @return " + tableName + " bean对象");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(final Method method, final TopLevelClass interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据查询条件更新" + introspectedTable.getFullyQualifiedTable() + "部分字段");
        method.addJavaDocLine(sb.toString());

        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");

        sb = new StringBuilder();
        sb.append(" * @param record 要更新成为的" + tableName + "对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param example 更新记录的查询条件");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int 更新记录数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(final Method method, final TopLevelClass interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据查询条件更新" + introspectedTable.getFullyQualifiedTable() + "全表字段");
        method.addJavaDocLine(sb.toString());

        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");

        sb = new StringBuilder();
        sb.append(" * @param record 要更新成为的" + tableName + "对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param example 更新记录的查询条件");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int 更新记录数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(final Method method, final TopLevelClass interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据主键更新" + introspectedTable.getFullyQualifiedTable() + "部分字段");
        method.addJavaDocLine(sb.toString());

        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");

        sb = new StringBuilder();
        sb.append(" * @param record 要更新成为的" + tableName + "对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int 更新记录数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(final Method method, final TopLevelClass interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据主键更新" + introspectedTable.getFullyQualifiedTable() + "全部字段");
        method.addJavaDocLine(sb.toString());

        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");

        sb = new StringBuilder();
        sb.append(" * @param record 要更新成为的" + tableName + "对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int 更新记录数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(final Method method, final Interface interfaze, final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        // sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据查询条件，计算" + introspectedTable.getFullyQualifiedTable() + "个数");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param example 通用查询条件类");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int 结果个数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(final Method method, final Interface interfaze, final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据查询条件，删除" + introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param example 通用查询条件类");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int  删除个数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(final Method method, final Interface interfaze, final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据属性名称，删除" + introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());
        final List<Parameter> params = method.getParameters();
        for (int i = 0; i < params.size(); i++) {
            final Parameter p = params.get(i);
            sb = new StringBuilder();
            sb.append(" * @param " + p.getName() + " " + p.getName());
            method.addJavaDocLine(sb.toString());
        }

        sb = new StringBuilder();
        sb.append(" * @return int  删除个数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientInsertMethodGenerated(final Method method, final Interface interfaze, final IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 插入一个" + introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param record " + introspectedTable.getFullyQualifiedTable() + "的bean对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int  插入个数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(final Method method, final Interface interfaze, final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 插入一个只有部分字段的" + introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param record 只含部分字段的" + introspectedTable.getFullyQualifiedTable() + "的bean对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int  插入个数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(final Method method, final Interface interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        // sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据查询条件类，返回" + introspectedTable.getFullyQualifiedTable() + "结果集");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param example 通用查询条件类");
        method.addJavaDocLine(sb.toString());

        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");
        sb = new StringBuilder();
        sb.append(" * @return List<" + tableName + ">" + introspectedTable.getFullyQualifiedTable() + "结果集");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(final Method method, final Interface interfaze, final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据主键类，返回" + introspectedTable.getFullyQualifiedTable());
        method.addJavaDocLine(sb.toString());
        final List<Parameter> params = method.getParameters();
        for (int i = 0; i < params.size(); i++) {
            final Parameter p = params.get(i);
            sb = new StringBuilder();
            sb.append(" * @param " + p.getName() + " " + p.getName());
            method.addJavaDocLine(sb.toString());
        }
        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");

        sb = new StringBuilder();
        sb.append(" * @return " + tableName + " bean对象");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(final Method method, final Interface interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据查询条件更新" + introspectedTable.getFullyQualifiedTable() + "部分字段");
        method.addJavaDocLine(sb.toString());

        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");

        sb = new StringBuilder();
        sb.append(" * @param record 要更新成为的" + tableName + "对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param criteria 更新记录的查询条件");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int 更新记录数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(final Method method, final Interface interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据查询条件更新" + introspectedTable.getFullyQualifiedTable() + "全表字段");
        method.addJavaDocLine(sb.toString());

        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");

        sb = new StringBuilder();
        sb.append(" * @param record 要更新成为的" + tableName + "对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @param criteria 更新记录的查询条件");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int 更新记录数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(final Method method, final Interface interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据主键更新" + introspectedTable.getFullyQualifiedTable() + "部分字段");
        method.addJavaDocLine(sb.toString());

        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");

        sb = new StringBuilder();
        sb.append(" * @param record 要更新成为的" + tableName + "对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int 更新记录数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(final Method method, final Interface interfaze,
        final IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Title "); //$NON-NLS-1$
        sb.append(interfaze.getType().getShortName());
        sb.append("."); //$NON-NLS-1$
        sb.append(method.getName());
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @Description: 根据主键更新" + introspectedTable.getFullyQualifiedTable() + "全部字段");
        method.addJavaDocLine(sb.toString());

        final String pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(pojoUrl + ".", "");

        sb = new StringBuilder();
        sb.append(" * @param record 要更新成为的" + tableName + "对象");
        method.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @return int 更新记录数");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$
        return true;
    }
}
