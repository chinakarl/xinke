package org.mybatis.generator.plugins;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TableConstantPlugin
 * @Description: 将表的所有字段都生成字段名常量
 * @author linyl linyuliang.85@gmail.com
 */
public class TableConstantPlugin extends PluginAdapter {

    // @Override
    // public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
    // IntrospectedTable introspectedTable) {
    // List<IntrospectedColumn> introspectedColumns =
    // introspectedTable.getAllColumns();
    // for (IntrospectedColumn introspectedColumn : introspectedColumns) {
    // Field field = new Field();
    // field.setVisibility(JavaVisibility.PUBLIC);
    // field.setStatic(true);
    // field.setFinal(true);
    // field.setType(new FullyQualifiedJavaType("java.lang.String"));
    //            field.setName(introspectedColumn.getActualColumnName().toUpperCase()); //$NON-NLS-1$
    // field.setInitializationString("\"" +
    // introspectedColumn.getActualColumnName() + "\"");
    // addFieldComment(field, "@Fields " +
    // introspectedColumn.getActualColumnName().toUpperCase() + ": " +
    // introspectedColumn.getRemarks());
    // topLevelClass.addField(field);
    // }
    // return true;
    // }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(final IntrospectedTable introspectedTable) {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        String beanClassName = introspectedTable.getBaseRecordType();
        String beanName = beanClassName.substring(beanClassName.lastIndexOf(".") + 1);
        final List<GeneratedJavaFile> files = new ArrayList<GeneratedJavaFile>();
        FullyQualifiedJavaType tableConstant = new FullyQualifiedJavaType(properties.getProperty("targetPackage")+table.getSubPackage()+ ".T" + beanName);
        final TopLevelClass topLevelClass = new TopLevelClass(tableConstant);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        topLevelClass.setAbstract(true);
        StringBuilder sb = new StringBuilder();

        topLevelClass.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @ClassName: "); //$NON-NLS-1$
        sb.append(topLevelClass.getType().getShortName());
        topLevelClass.addJavaDocLine(sb.toString()); //$NON-NLS-1$

        sb = new StringBuilder();
        sb.append(" * @Description: ");
        sb.append(table.toString() + "表结构对应的常量类，定义字段名常量");
        topLevelClass.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @author: Interests");
        topLevelClass.addJavaDocLine(sb.toString());

        topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$

        List<IntrospectedColumn> introspectedColumns = introspectedTable.getAllColumns();
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            Field field = new Field();
            field.setVisibility(JavaVisibility.PUBLIC);
            field.setStatic(true);
            field.setFinal(true);
            field.setType(new FullyQualifiedJavaType("java.lang.String"));
            field.setName(introspectedColumn.getActualColumnName().toUpperCase()); //$NON-NLS-1$
            field.setInitializationString("\"" + introspectedColumn.getActualColumnName() + "\"");
            addFieldComment(field, "@Fields " + table.toString() + "." + introspectedColumn.getActualColumnName().toUpperCase() + ": "
                + introspectedColumn.getRemarks());
            topLevelClass.addField(field);
        }

        final GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass, context.getJavaModelGeneratorConfiguration().getTargetProject());
        files.add(file);
        return files;
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }
}
