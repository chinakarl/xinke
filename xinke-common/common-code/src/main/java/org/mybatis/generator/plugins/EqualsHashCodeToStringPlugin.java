/*
 * Copyright 2008 The Apache Software Foundation Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package org.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;

/**
 * This plugin adds equals(), toString() and hashCode() methods to the generated model classes. It demonstrates the process of adding methods to
 * generated classes
 * <p>
 * The <tt>equals</tt> method generated by this class is correct in most cases, but will probably NOT be correct if you have specified a rootClass -
 * because our equals method only checks the fields it knows about.
 * <p>
 * Similarly, the <tt>hashCode</tt> method generated by this class only relies on fields it knows about. Anything you add, or fields in a super class
 * will not be factored into the hash code.
 * @author Jeff Butler
 */
public class EqualsHashCodeToStringPlugin extends PluginAdapter {

    public EqualsHashCodeToStringPlugin() {
    }

    /**
     * This plugin is always valid - no properties are required
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // generateEquals(topLevelClass, introspectedTable);
        // generateHashCode(topLevelClass, introspectedTable);
        // generateToString(topLevelClass, introspectedTable);
        // generateClone(topLevelClass, introspectedTable);
        extendsStringAndEqualsObject(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // generateEquals(topLevelClass, introspectedTable);
        // generateHashCode(topLevelClass, introspectedTable);
        // generateToString(topLevelClass, introspectedTable);
        // generateClone(topLevelClass, introspectedTable);
        extendsStringAndEqualsObject(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // generateEquals(topLevelClass, introspectedTable);
        // generateHashCode(topLevelClass, introspectedTable);
        // generateToString(topLevelClass, introspectedTable);
        // generateClone(topLevelClass, introspectedTable);
        extendsStringAndEqualsObject(topLevelClass, introspectedTable);
        return true;
    }

    protected void extendsStringAndEqualsObject(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (null == topLevelClass.getSuperClass()) {
            topLevelClass.addImportedType(new FullyQualifiedJavaType(properties.getProperty("stringAndEqualsObject")));
            topLevelClass.setSuperClass(properties.getProperty("stringAndEqualsObject"));
        }
    }

    /**
     * Generates an <tt>equals</tt> method that does a comparison of all fields.
     * <p>
     * The generated <tt>equals</tt> method will be correct unless:
     * <ul>
     * <li>Other fields have been added to the generated classes</li>
     * <li>A <tt>rootClass</tt> is specified that holds state</li>
     * </ul>
     * @param topLevelClass the class to which the method will be added
     * @param introspectedTable the table corresponding to this class
     */
    protected void generateEquals(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.apache.commons.lang.builder.EqualsBuilder"));
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        method.setName("equals"); //$NON-NLS-1$
        method.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "obj")); //$NON-NLS-1$
        if (introspectedTable.isJava5Targeted()) {
            method.addAnnotation("@Override"); //$NON-NLS-1$
        }

        // context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        method.addBodyLine("return EqualsBuilder.reflectionEquals(this, obj);"); //$NON-NLS-1$

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/*"); //$NON-NLS-1$
        sb.append("* (非 Javadoc) <p>Title: "); //$NON-NLS-1$
        sb.append(topLevelClass.getType().getShortName());
        sb.append(".equals</p>"); //$NON-NLS-1$
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine("*/"); //$NON-NLS-1$

        topLevelClass.addMethod(method);
    }

    /**
     * Generates a <tt>hashCode</tt> method that includes all fields.
     * <p>
     * Note that this implementation is based on the eclipse foundation hashCode generator.
     * @param topLevelClass the class to which the method will be added
     * @param introspectedTable the table corresponding to this class
     */
    protected void generateHashCode(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.apache.commons.lang.builder.HashCodeBuilder"));
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName("hashCode"); //$NON-NLS-1$
        if (introspectedTable.isJava5Targeted()) {
            method.addAnnotation("@Override"); //$NON-NLS-1$
        }

        // context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        method.addBodyLine("return HashCodeBuilder.reflectionHashCode(this);"); //$NON-NLS-1$

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/*"); //$NON-NLS-1$
        sb.append("* (非 Javadoc) <p>Title: "); //$NON-NLS-1$
        sb.append(topLevelClass.getType().getShortName());
        sb.append(".hashCode</p>"); //$NON-NLS-1$
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine("*/"); //$NON-NLS-1$

        topLevelClass.addMethod(method);

    }

    /**
     * Generates a <tt>toString</tt> method that includes all fields.
     * <p>
     * Note that this implementation is based on the eclipse foundation hashCode generator.
     * @param topLevelClass the class to which the method will be added
     * @param introspectedTable the table corresponding to this class
     */
    protected void generateToString(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.apache.commons.lang.builder.ToStringBuilder"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.apache.commons.lang.builder.ToStringStyle"));
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
        method.setName("toString"); //$NON-NLS-1$
        if (introspectedTable.isJava5Targeted()) {
            method.addAnnotation("@Override"); //$NON-NLS-1$
        }

        // context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        method.addBodyLine("return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);"); //$NON-NLS-1$

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/*"); //$NON-NLS-1$
        sb.append("* (非 Javadoc) <p>Title: "); //$NON-NLS-1$
        sb.append(topLevelClass.getType().getShortName());
        sb.append(".toString</p>"); //$NON-NLS-1$
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine("*/"); //$NON-NLS-1$

        topLevelClass.addMethod(method);
    }

    /**
     * @Title: generateClone
     * @Description: 生成clone类
     * @param topLevelClass 首页
     * @param introspectedTable 表
     */
    protected void generateClone(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.apache.commons.beanutils.BeanUtils"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.lang.reflect.InvocationTargetException"));
        topLevelClass.addSuperInterface(new FullyQualifiedJavaType("java.lang.Cloneable"));
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(topLevelClass.getType());
        method.setName("clone"); //$NON-NLS-1$
        if (introspectedTable.isJava5Targeted()) {
            method.addAnnotation("@Override"); //$NON-NLS-1$
        }

        // context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        method.addBodyLine("try {");
        method.addBodyLine("return (" + topLevelClass.getType().getShortName() + ") BeanUtils.cloneBean(this);");
        method.addBodyLine("} catch (IllegalAccessException e) {");
        method.addBodyLine("throw new RuntimeException(e);");
        method.addBodyLine("} catch (InstantiationException e) {");
        method.addBodyLine("throw new RuntimeException(e);");
        method.addBodyLine("} catch (InvocationTargetException e) {");
        method.addBodyLine("throw new RuntimeException(e);");
        method.addBodyLine("} catch (NoSuchMethodException e) {");
        method.addBodyLine("throw new RuntimeException(e);");
        method.addBodyLine("}");

        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/*"); //$NON-NLS-1$
        sb.append("* (非 Javadoc) <p>Title: "); //$NON-NLS-1$
        sb.append(topLevelClass.getType().getShortName());
        sb.append(".clone</p>"); //$NON-NLS-1$
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine("*/"); //$NON-NLS-1$

        topLevelClass.addMethod(method);
    }
}
