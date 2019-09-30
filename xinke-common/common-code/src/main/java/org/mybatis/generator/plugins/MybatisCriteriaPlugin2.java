package org.mybatis.generator.plugins;

import org.apache.log4j.Logger;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * 增加公用条件查询类 Criteria
 * @author QQ:34847009
 * @date 2010-10-21 下午09:33:48
 */
public class MybatisCriteriaPlugin2 extends PluginAdapter {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(MybatisCriteriaPlugin2.class);

    private FullyQualifiedJavaType criteria;
    /** 数据库类型 */
    private String databaseType;

    /**
     * 是否把所有条件就设置到一个里面
     */
    private boolean isAllInOne;

    @Override
    public boolean validate(final List<String> warnings) {
        databaseType = context.getJdbcConnectionConfiguration().getDriverClass();
        final String criterias = context.getJavaModelGeneratorConfiguration().getTargetPackage() + ".Criteria";
        criteria = new FullyQualifiedJavaType(criterias);
        if (stringHasValue(properties.getProperty("isAllInOne"))) { //$NON-NLS-1$
            isAllInOne = StringUtility.isTrue(properties.getProperty("isAllInOne"));
        } else {
            isAllInOne = false;
        }
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(final IntrospectedTable introspectedTable) {

        final List<GeneratedJavaFile> files = new ArrayList<GeneratedJavaFile>();
        final TopLevelClass topLevelClass = new TopLevelClass(criteria);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        addClassComment(topLevelClass, "公用条件查询类");
        topLevelClass.addImportedType(FullyQualifiedJavaType.getNewMapInstance());
        topLevelClass.addImportedType(FullyQualifiedJavaType.getNewHashMapInstance());

        final FullyQualifiedJavaType types = new FullyQualifiedJavaType("java.util.Map<java.lang.String, java.lang.Object>");
        final Rules rules = introspectedTable.getRules();
        if (rules.generateUpdateByExampleSelective() || rules.generateUpdateByExampleWithBLOBs() || rules.generateUpdateByExampleWithoutBLOBs()) {
            final Method method = new Method();
            method.setVisibility(JavaVisibility.PROTECTED);
            method.setConstructor(true);
            method.setName(criteria.getShortName());
            method.addParameter(new Parameter(criteria, "example")); //$NON-NLS-1$
            method.addBodyLine("this.orderByClause = example.orderByClause;"); //$NON-NLS-1$
            method.addBodyLine("this.condition = example.condition;"); //$NON-NLS-1$
            method.addBodyLine("this.distinct = example.distinct;"); //$NON-NLS-1$
            if (databaseType.contains("oracle")) {
                method.addBodyLine("this.oracleStart = example.oracleStart;"); //$NON-NLS-1$
                method.addBodyLine("this.oracleEnd = example.oracleEnd;"); //$NON-NLS-1$
            }
            if (databaseType.contains("mysql")) {
                method.addBodyLine("this.mysqlLength = example.mysqlLength;"); //$NON-NLS-1$
                method.addBodyLine("this.mysqlOffset = example.mysqlOffset;"); //$NON-NLS-1$
            }
            topLevelClass.addMethod(method);
        }

        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(types);
        field.setName("condition"); //$NON-NLS-1$
        addFieldComment(field, "存放条件查询值");
        topLevelClass.addField(field);

        // add field, getter, setter for distinct
        field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        field.setName("distinct"); //$NON-NLS-1$
        addFieldComment(field, "是否相异");
        topLevelClass.addField(field);

        // add field, getter, setter for orderby clause
        field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(FullyQualifiedJavaType.getStringInstance());
        field.setName("orderByClause"); //$NON-NLS-1$
        addFieldComment(field, "排序字段");
        topLevelClass.addField(field);

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        method.setName("Criteria"); //$NON-NLS-1$
        method.addBodyLine("condition = new HashMap<String, Object>();"); //$NON-NLS-1$
        topLevelClass.addMethod(method);

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("clear"); //$NON-NLS-1$
        method.addBodyLine("condition.clear();"); //$NON-NLS-1$
        method.addBodyLine("orderByClause = null;"); //$NON-NLS-1$
        method.addBodyLine("distinct = false;"); //$NON-NLS-1$
        if (databaseType.contains("oracle")) {
            method.addBodyLine("this.oracleStart=null;"); //$NON-NLS-1$
            method.addBodyLine("this.oracleEnd=null;"); //$NON-NLS-1$
        }
        if (databaseType.contains("mysql")) {
            method.addBodyLine("this.mysqlOffset = null;"); //$NON-NLS-1$
            method.addBodyLine("this.mysqlLength = null;"); //$NON-NLS-1$
        }
        topLevelClass.addMethod(method);

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(criteria);
        method.setName("put"); //$NON-NLS-1$
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition")); //$NON-NLS-1$
        method.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "value")); //$NON-NLS-1$
        method.addBodyLine("this.condition.put(condition, value);"); //$NON-NLS-1$
        method.addBodyLine("return (Criteria) this;"); //$NON-NLS-1$
        addSetterComment(method, OutputUtilities.lineSeparator + "\t *            查询的条件名称" + OutputUtilities.lineSeparator + "\t * @param value"
            + OutputUtilities.lineSeparator + "\t *            查询的值", "condition");
        topLevelClass.addMethod(method);

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("setOrderByClause"); //$NON-NLS-1$
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "orderByClause")); //$NON-NLS-1$
        method.addBodyLine("this.orderByClause = orderByClause;"); //$NON-NLS-1$
        addSetterComment(method, OutputUtilities.lineSeparator + "\t *            排序字段", "orderByClause");
        topLevelClass.addMethod(method);

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("setDistinct"); //$NON-NLS-1$
        method.addParameter(new Parameter(FullyQualifiedJavaType.getBooleanPrimitiveInstance(), "distinct")); //$NON-NLS-1$
        method.addBodyLine("this.distinct = distinct;"); //$NON-NLS-1$
        addSetterComment(method, OutputUtilities.lineSeparator + "\t *            是否相异", "distinct");
        topLevelClass.addMethod(method);

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("setCondition"); //$NON-NLS-1$
        method.addParameter(new Parameter(types, "condition")); //$NON-NLS-1$
        method.addBodyLine("this.condition = condition;"); //$NON-NLS-1$
        method.addDeprecated();
        topLevelClass.addMethod(method);

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(types);
        method.setName("getCondition"); //$NON-NLS-1$
        method.addBodyLine("return condition;"); //$NON-NLS-1$
        method.addDeprecated();
        topLevelClass.addMethod(method);
        if (databaseType.contains("oracle")) {
            // 增加开始处
            // 增加oracleStart、oracleEnd、mysqlOffset、mysqlLength
            // add field, getter, setter for oracleStart clause
            field = new Field();
            field.setVisibility(JavaVisibility.PRIVATE);
            field.setType(FullyQualifiedJavaType.getInteger());
            field.setName("oracleStart"); //$NON-NLS-1$
            topLevelClass.addField(field);

            method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setName("setOracleStart"); //$NON-NLS-1$
            method.addParameter(new Parameter(FullyQualifiedJavaType.getInteger(), "oracleStart")); //$NON-NLS-1$
            method.addBodyLine("this.oracleStart = oracleStart;"); //$NON-NLS-1$
            addSetterComment(method, "开始记录数", "oracleStart");
            topLevelClass.addMethod(method);

            // add field, getter, setter for oracleEnd clause
            field = new Field();
            field.setVisibility(JavaVisibility.PRIVATE);
            field.setType(FullyQualifiedJavaType.getInteger());
            field.setName("oracleEnd"); //$NON-NLS-1$
            topLevelClass.addField(field);

            method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setName("setOracleEnd"); //$NON-NLS-1$
            method.addParameter(new Parameter(FullyQualifiedJavaType.getInteger(), "oracleEnd")); //$NON-NLS-1$
            method.addBodyLine("this.oracleEnd = oracleEnd;"); //$NON-NLS-1$
            addSetterComment(method, "结束记录数", "oracleEnd");
            topLevelClass.addMethod(method);

        } else if (databaseType.contains("mysql")) {
            // add field, getter, setter for mysqlOffset clause
            field = new Field();
            field.setVisibility(JavaVisibility.PRIVATE);
            field.setType(FullyQualifiedJavaType.getInteger());
            field.setName("mysqlOffset"); //$NON-NLS-1$
            topLevelClass.addField(field);

            method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setName("setMysqlOffset"); //$NON-NLS-1$
            method.addParameter(new Parameter(FullyQualifiedJavaType.getInteger(), "mysqlOffset")); //$NON-NLS-1$
            method.addBodyLine("this.mysqlOffset = mysqlOffset;"); //$NON-NLS-1$
            addSetterComment(method, OutputUtilities.lineSeparator + "\t *            指定返回记录行的偏移量<br>" + OutputUtilities.lineSeparator
                + "\t *            " + "mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15", "mysqlOffset");
            topLevelClass.addMethod(method);

            // add field, getter, setter for mysqlLength clause
            field = new Field();
            field.setVisibility(JavaVisibility.PRIVATE);
            field.setType(FullyQualifiedJavaType.getInteger());
            field.setName("mysqlLength"); //$NON-NLS-1$
            topLevelClass.addField(field);

            method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setName("setMysqlLength"); //$NON-NLS-1$
            method.addParameter(new Parameter(FullyQualifiedJavaType.getInteger(), "mysqlLength")); //$NON-NLS-1$
            method.addBodyLine("this.mysqlLength = mysqlLength;"); //$NON-NLS-1$
            addSetterComment(method, OutputUtilities.lineSeparator + "\t *            指定返回记录行的最大数目" + "<br>" + OutputUtilities.lineSeparator
                + "\t *            mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15", "mysqlLength");
            topLevelClass.addMethod(method);

            // 增加结束处
        }

        final GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass, context.getJavaModelGeneratorConfiguration().getTargetProject());
        files.add(file);
        return files;
    }

    @Override
    public boolean clientGenerated(final Interface interfaze, final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        // 接口方法
        final List<Method> methods = interfaze.getMethods();
        final Parameter parameter = new Parameter(criteria, "example", "@Param(\"example\")");
        final Parameter parameter2 = new Parameter(criteria, "example");
        boolean first = true;
        for (int i = 0; i < methods.size(); i++) {
            final Method method = methods.get(i);
            if (method.getFormattedContent(0, true).contains("Example")) {
                final int size = method.getParameters().size();
                if (first) {
                    interfaze.removeImportedType(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
                    interfaze.addImportedType(criteria);
                }
                if (size == 1) {
                    method.removeParameter(0);
                    if (isAllInOne) {
                        method.addParameter(parameter);
                    } else {
                        method.addParameter(parameter2);
                    }
                } else if (size == 2) {
                    method.removeParameter(1);
                    method.addParameter(1, parameter);
                }
                first = false;
            }
        }

        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(final Document document, final IntrospectedTable introspectedTable) {
        // 移除第2个
        // document.getRootElement().getElements().remove(2);

        // 替换所有条件parameterType中的pojoExample
        final List<Element> list = document.getRootElement().getElements();
        logger.info(list.size() + "");
        for (int i = 0; i < list.size(); i++) {
            XmlElement xml = (XmlElement) list.get(i);
            final String content = xml.getFormattedContent(0);
            if (content.contains(introspectedTable.getExampleType())) {
                final List<Attribute> attrs = xml.getAttributes();
                for (int j = 0; j < attrs.size(); j++) {
                    if (attrs.get(j).getName().equals("parameterType")) {
                        attrs.get(j).setValue(criteria.getFullyQualifiedName());
                    }
                }
            } else if (content.contains("Update_By_Example_Where_Clause") && isAllInOne) {
                if (xml.getElements().size() == 3) {
                    xml = (XmlElement) xml.getElements().get(2);
                    xml = (XmlElement) xml.getElements().get(0);
                    xml.getAttributes().get(0).setValue("Example_Where_Clause");
                } else {
                    for (int j = 0; j < xml.getElements().size(); j++) {
                        final Element xmls = xml.getElements().get(j);
                        if (xmls.getFormattedContent(0).contains("Update_By_Example_Where_Clause")) {
                            xml = (XmlElement) xmls;
                            xml = (XmlElement) xml.getElements().get(0);
                            xml.getAttributes().get(0).setValue("Example_Where_Clause");
                        }
                    }
                }
            }
        }
        // Example_Where_Clause
        XmlElement Where_Clause = (XmlElement) document.getRootElement().getElements().get(1);
        // 移除第一个
        Where_Clause.removeElement(0);

        StringBuilder sb = new StringBuilder();
        XmlElement dynamicElement = new XmlElement("trim"); //$NON-NLS-1$
        dynamicElement.addAttribute(new Attribute("prefix", "where")); //$NON-NLS-1$ //$NON-NLS-2$
        dynamicElement.addAttribute(new Attribute("prefixOverrides", "and|or")); //$NON-NLS-1$ //$NON-NLS-2$
        Where_Clause.addElement(dynamicElement);

        for (final IntrospectedColumn introspectedColumn : introspectedTable.getNonPrimaryKeyColumns()) {
            final XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
            if (isAllInOne) {
                final String va = introspectedColumn.getJavaProperty("example.condition.") + " != null";
                isNotNullElement.addAttribute(new Attribute("test", va)); //$NON-NLS-1$ //$NON-NLS-2$
            } else {
                final String va = introspectedColumn.getJavaProperty("condition.") + " != null";
                isNotNullElement.addAttribute(new Attribute("test", va)); //$NON-NLS-1$ //$NON-NLS-2$
            }
            dynamicElement.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append(" and "); //$NON-NLS-1$
            sb.append(Ibatis2FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = "); //$NON-NLS-1$
            if (isAllInOne) {
                sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "example.condition."));

            } else {

                sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "condition."));
            }
            isNotNullElement.addElement(new TextElement(sb.toString()));
        }
        if (!isAllInOne) {

            // Example_Where_Clause
            Where_Clause = (XmlElement) document.getRootElement().getElements().get(2);
            // 移除第一个
            Where_Clause.removeElement(0);

            sb = new StringBuilder();
            dynamicElement = new XmlElement("trim"); //$NON-NLS-1$
            dynamicElement.addAttribute(new Attribute("prefix", "where")); //$NON-NLS-1$ //$NON-NLS-2$
            dynamicElement.addAttribute(new Attribute("prefixOverrides", "and|or")); //$NON-NLS-1$ //$NON-NLS-2$
            Where_Clause.addElement(dynamicElement);

            for (final IntrospectedColumn introspectedColumn : introspectedTable.getNonPrimaryKeyColumns()) {
                final XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
                final String va = introspectedColumn.getJavaProperty("example.condition.") + " != null";
                isNotNullElement.addAttribute(new Attribute("test", va)); //$NON-NLS-1$ //$NON-NLS-2$
                dynamicElement.addElement(isNotNullElement);

                sb.setLength(0);
                sb.append(" and "); //$NON-NLS-1$
                sb.append(Ibatis2FormattingUtilities.getEscapedColumnName(introspectedColumn));
                sb.append(" = "); //$NON-NLS-1$
                sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "example.condition."));
                isNotNullElement.addElement(new TextElement(sb.toString()));
            }
        } else {
            document.getRootElement().getElements().remove(2);
        }
        return true;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(final XmlElement element, final IntrospectedTable introspectedTable) {
        // if (databaseType.contains("oracle")) {
        // final XmlElement oracleHeadIncludeElement = new XmlElement("include");
        // oracleHeadIncludeElement.addAttribute(new Attribute("refid", "common.Oracle_Pagination_Head"));
        // // 在第一个地方增加
        // element.addElement(0, oracleHeadIncludeElement);
        //
        // final XmlElement oracleTailIncludeElement = new XmlElement("include");
        // oracleTailIncludeElement.addAttribute(new Attribute("refid", "common.Oracle_Pagination_Tail"));
        // // 在最后增加
        // element.addElement(element.getElements().size(), oracleTailIncludeElement);
        // } else if (databaseType.contains("mysql")) {
        // final XmlElement mysqlLimitIncludeElement = new XmlElement("include");
        // mysqlLimitIncludeElement.addAttribute(new Attribute("refid", "common.Mysql_Pagination_Limit"));
        // // 在最后增加
        // element.addElement(element.getElements().size(), mysqlLimitIncludeElement);
        // }
        return true;
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(final XmlElement element, final IntrospectedTable introspectedTable) {
        // if (databaseType.contains("oracle")) {
        // final XmlElement oracleHeadIncludeElement = new XmlElement("include");
        // oracleHeadIncludeElement.addAttribute(new Attribute("refid", "common.Oracle_Pagination_Head"));
        // // 在第一个地方增加
        // element.addElement(0, oracleHeadIncludeElement);
        //
        // final XmlElement oracleTailIncludeElement = new XmlElement("include");
        // oracleTailIncludeElement.addAttribute(new Attribute("refid", "common.Oracle_Pagination_Tail"));
        // // 在最后增加
        // element.addElement(element.getElements().size(), oracleTailIncludeElement);
        // } else if (databaseType.contains("mysql")) {
        // final XmlElement mysqlLimitIncludeElement = new XmlElement("include");
        // mysqlLimitIncludeElement.addAttribute(new Attribute("refid", "common.Mysql_Pagination_Limit"));
        // // 在最后增加
        // element.addElement(element.getElements().size(), mysqlLimitIncludeElement);
        // }
        return true;
    }

    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(final IntrospectedTable introspectedTable) {
        final Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID, XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
        final XmlElement answer = new XmlElement("mapper"); //$NON-NLS-1$
        document.setRootElement(answer);
        answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
            "common"));

        // if (databaseType.contains("oracle")) {
        // answer.addElement(getOracleHead());
        // answer.addElement(getOracleTail());
        // } else if (databaseType.contains("mysql")) {
        // answer.addElement(getMysqlLimit());
        // }

        final GeneratedXmlFile gxf = new GeneratedXmlFile(document, properties.getProperty("fileName", "common_SqlMap.xml"), //$NON-NLS-1$ //$NON-NLS-2$
            context.getSqlMapGeneratorConfiguration().getTargetPackage(), //$NON-NLS-1$
            context.getSqlMapGeneratorConfiguration().getTargetProject(), //$NON-NLS-1$
            false);

        final List<GeneratedXmlFile> files = new ArrayList<GeneratedXmlFile>(1);
        files.add(gxf);
        return files;
    }

    private XmlElement getOracleHead() {
        final XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", "Oracle_Pagination_Head")); //$NON-NLS-1$

        final XmlElement outerisNotEmptyElement = new XmlElement("if");
        outerisNotEmptyElement.addAttribute(new Attribute("test", "oracleStart != null and oracleEnd != null"));
        outerisNotEmptyElement.addElement(new TextElement("<![CDATA[ select * from ( select row_.*, rownum rownum_ from ( ]]>"));
        answer.addElement(outerisNotEmptyElement);
        return answer;
    }

    private XmlElement getOracleTail() {
        final XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", "Oracle_Pagination_Tail")); //$NON-NLS-1$

        final XmlElement outerisNotEmptyElement = new XmlElement("if");
        outerisNotEmptyElement.addAttribute(new Attribute("test", "oracleStart != null and oracleEnd != null"));
        outerisNotEmptyElement.addElement(new TextElement("<![CDATA[ ) row_ where rownum <= #{oracleEnd} ) where rownum_ > #{oracleStart} ]]>"));
        answer.addElement(outerisNotEmptyElement);
        return answer;
    }

    private XmlElement getMysqlLimit() {
        final XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", "Mysql_Pagination_Limit")); //$NON-NLS-1$

        final XmlElement outerisNotEmptyElement = new XmlElement("if");
        outerisNotEmptyElement.addAttribute(new Attribute("test", "mysqlOffset != null and mysqlLength != null"));
        outerisNotEmptyElement.addElement(new TextElement("<![CDATA[ limit #{mysqlOffset} , #{mysqlLength} ]]>"));
        answer.addElement(outerisNotEmptyElement);
        return answer;
    }
}
