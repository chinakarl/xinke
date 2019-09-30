package org.mybatis.generator.plugins;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成service类
 * @author QQ:34847009
 * @date 2010-12-6 上午10:34:45
 */
public class MybatisServicePlugin extends PluginAdapter {

    private final FullyQualifiedJavaType slf4jLogger;
    private final FullyQualifiedJavaType slf4jLoggerFactory;
    private FullyQualifiedJavaType serviceType;
    private FullyQualifiedJavaType daoType;
    private FullyQualifiedJavaType interfaceType;
    private FullyQualifiedJavaType pojoType;
    private FullyQualifiedJavaType pojoPrimaryType;
    private FullyQualifiedJavaType pojoCriteriaType;
    private FullyQualifiedJavaType listType;
    private FullyQualifiedJavaType autowired;
    private FullyQualifiedJavaType service;
    private FullyQualifiedJavaType returnType;
    private String servicePack;
    private String serviceImplPack;
    private String project;
    private String pojoUrl;

    private final List<Method> methods;
    /**
     * 是否添加注解
     */
    private boolean enableAnnotation = true;
    private boolean enableInsert = false;
    private boolean enableInsertSelective = false;
    private boolean enableDeleteByPrimaryKey = false;
    private boolean enableDeleteByExample = false;
    private boolean enableUpdateByExample = false;
    private boolean enableUpdateByExampleSelective = false;
    private boolean enableUpdateByPrimaryKey = false;
    private boolean enableUpdateByPrimaryKeySelective = false;

    public MybatisServicePlugin() {
        super();
        // 默认是slf4j
        slf4jLogger = new FullyQualifiedJavaType("org.slf4j.Logger");
        slf4jLoggerFactory = new FullyQualifiedJavaType("org.slf4j.LoggerFactory");
        methods = new ArrayList<Method>();
    }

    @Override
    public boolean validate(final List<String> warnings) {
        if (StringUtility.stringHasValue(properties.getProperty("enableAnnotation"))) {
            enableAnnotation = StringUtility.isTrue(properties.getProperty("enableAnnotation"));
        }

        final String enableInsert = properties.getProperty("enableInsert");

        final String enableUpdateByExampleSelective = properties.getProperty("enableUpdateByExampleSelective");

        final String enableInsertSelective = properties.getProperty("enableInsertSelective");

        final String enableUpdateByPrimaryKey = properties.getProperty("enableUpdateByPrimaryKey");

        final String enableDeleteByPrimaryKey = properties.getProperty("enableDeleteByPrimaryKey");

        final String enableDeleteByExample = properties.getProperty("enableDeleteByExample");

        final String enableUpdateByPrimaryKeySelective = properties.getProperty("enableUpdateByPrimaryKeySelective");

        final String enableUpdateByExample = properties.getProperty("enableUpdateByExample");

        if (StringUtility.stringHasValue(enableInsert)) {
            this.enableInsert = StringUtility.isTrue(enableInsert);
        }
        if (StringUtility.stringHasValue(enableUpdateByExampleSelective)) {
            this.enableUpdateByExampleSelective = StringUtility.isTrue(enableUpdateByExampleSelective);
        }
        if (StringUtility.stringHasValue(enableInsertSelective)) {
            this.enableInsertSelective = StringUtility.isTrue(enableInsertSelective);
        }
        if (StringUtility.stringHasValue(enableUpdateByPrimaryKey)) {
            this.enableUpdateByPrimaryKey = StringUtility.isTrue(enableUpdateByPrimaryKey);
        }
        if (StringUtility.stringHasValue(enableDeleteByPrimaryKey)) {
            this.enableDeleteByPrimaryKey = StringUtility.isTrue(enableDeleteByPrimaryKey);
        }
        if (StringUtility.stringHasValue(enableDeleteByExample)) {
            this.enableDeleteByExample = StringUtility.isTrue(enableDeleteByExample);
        }
        if (StringUtility.stringHasValue(enableUpdateByPrimaryKeySelective)) {
            this.enableUpdateByPrimaryKeySelective = StringUtility.isTrue(enableUpdateByPrimaryKeySelective);
        }
        if (StringUtility.stringHasValue(enableUpdateByExample)) {
            this.enableUpdateByExample = StringUtility.isTrue(enableUpdateByExample);
        }

        servicePack = properties.getProperty("targetPackage");
        serviceImplPack = properties.getProperty("implementationPackage");
        project = properties.getProperty("targetProject");

        pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();

        if (enableAnnotation) {
            autowired = new FullyQualifiedJavaType("javax.annotation.Resource");
            service = new FullyQualifiedJavaType("org.springframework.stereotype.Repository");
        }
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(final IntrospectedTable introspectedTable) {
        final List<GeneratedJavaFile> files = new ArrayList<GeneratedJavaFile>();
        final String table = introspectedTable.getBaseRecordType();
        final String tableName = table.replaceAll(this.pojoUrl + ".", "");
        interfaceType = new FullyQualifiedJavaType(servicePack + "." + tableName + "Service");

        // mybatis
        daoType = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());

        // logger.info(toLowerCase(daoType.getShortName()));
        // serviceType = new FullyQualifiedJavaType(serviceImplPack + "." +
        // tableName + "ServiceImpl");
        serviceType = new FullyQualifiedJavaType(serviceImplPack + "." + tableName + "Dao");

        pojoType = new FullyQualifiedJavaType(pojoUrl + "." + tableName);
        pojoPrimaryType = new FullyQualifiedJavaType(pojoUrl + "." + tableName + "Key");
        pojoCriteriaType = new FullyQualifiedJavaType(context.getJavaClientGeneratorConfiguration().getProperty("criteria"));
        listType = new FullyQualifiedJavaType("java.util.List");
        final Interface interface1 = new Interface(interfaceType);
        final TopLevelClass topLevelClass = new TopLevelClass(serviceType);
        // 导入必要的类
        addImport(interface1, topLevelClass);

        // 接口
        // addService(interface1, introspectedTable, tableName, files);

        // 实现类
        addServiceImpl(topLevelClass, introspectedTable, tableName, files);

        return files;
    }

    /**
     * 添加接口
     * @param tableName
     * @param files
     */
    protected void addService(final Interface interface1, final IntrospectedTable introspectedTable, final String tableName,
        final List<GeneratedJavaFile> files) {

        interface1.setVisibility(JavaVisibility.PUBLIC);
        if (introspectedTable.getRules().generatePrimaryKeyClass()) {
            interface1.addImportedType(pojoPrimaryType);
        }
        StringBuilder sb = new StringBuilder();

        interface1.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @ClassName: "); //$NON-NLS-1$
        sb.append(interface1.getType().getShortName());
        interface1.addJavaDocLine(sb.toString()); //$NON-NLS-1$

        sb = new StringBuilder();
        sb.append(" * @Description: ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append("表对应的dao操作封装接口类");
        interface1.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @author: Interests");
        interface1.addJavaDocLine(sb.toString());

        interface1.addJavaDocLine(" */"); //$NON-NLS-1$

        // 添加方法
        Method method = countByExample(introspectedTable, tableName);
        method.removeAllBodyLines();
        method.removeAnnotation();
        context.getPlugins().clientCountByExampleMethodGenerated(method, interface1, introspectedTable);
        interface1.addMethod(method);

        boolean hasPrimaryKey = introspectedTable.hasPrimaryKeyColumns();
        if (hasPrimaryKey) {
            method = selectByPrimaryKey(introspectedTable, tableName);
            method.removeAllBodyLines();
            method.removeAnnotation();
            context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interface1, introspectedTable);
            interface1.addMethod(method);
        }

        method = selectByExample(introspectedTable, tableName);
        method.removeAllBodyLines();
        method.removeAnnotation();
        context.getPlugins().clientSelectByExampleWithBLOBsMethodGenerated(method, interface1, introspectedTable);
        interface1.addMethod(method);

        if (enableDeleteByPrimaryKey && hasPrimaryKey) {
            method = getOtherInteger("deleteByPrimaryKey", introspectedTable, tableName, 2);
            method.removeAllBodyLines();
            method.removeAnnotation();
            context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(method, interface1, introspectedTable);
            interface1.addMethod(method);
        }
        if (enableUpdateByPrimaryKeySelective && hasPrimaryKey) {
            method = getOtherInteger("updateByPrimaryKeySelective", introspectedTable, tableName, 1);
            method.removeAllBodyLines();
            method.removeAnnotation();
            context.getPlugins().clientUpdateByPrimaryKeySelectiveMethodGenerated(method, interface1, introspectedTable);
            interface1.addMethod(method);
        }
        if (enableUpdateByPrimaryKey && hasPrimaryKey) {
            method = getOtherInteger("updateByPrimaryKey", introspectedTable, tableName, 1);
            method.removeAllBodyLines();
            method.removeAnnotation();
            context.getPlugins().clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method, interface1, introspectedTable);
            interface1.addMethod(method);
        }
        if (enableDeleteByExample) {
            method = getOtherInteger("deleteByExample", introspectedTable, tableName, 3);
            method.removeAllBodyLines();
            method.removeAnnotation();
            context.getPlugins().clientDeleteByExampleMethodGenerated(method, interface1, introspectedTable);
            interface1.addMethod(method);
        }
        if (enableUpdateByExampleSelective) {
            method = getOtherInteger("updateByExampleSelective", introspectedTable, tableName, 4);
            method.removeAllBodyLines();
            method.removeAnnotation();
            context.getPlugins().clientUpdateByExampleSelectiveMethodGenerated(method, interface1, introspectedTable);
            interface1.addMethod(method);
        }
        if (enableUpdateByExample) {
            method = getOtherInteger("updateByExample", introspectedTable, tableName, 4);
            method.removeAllBodyLines();
            method.removeAnnotation();
            context.getPlugins().clientUpdateByExampleWithBLOBsMethodGenerated(method, interface1, introspectedTable);
            interface1.addMethod(method);
        }
        if (enableInsert) {
            method = getOtherInsertboolean("insert", introspectedTable, tableName);
            method.removeAllBodyLines();
            method.removeAnnotation();
            context.getPlugins().clientInsertMethodGenerated(method, interface1, introspectedTable);
            interface1.addMethod(method);
        }
        if (enableInsertSelective) {
            method = getOtherInsertboolean("insertSelective", introspectedTable, tableName);
            method.removeAllBodyLines();
            method.removeAnnotation();
            context.getPlugins().clientInsertSelectiveMethodGenerated(method, interface1, introspectedTable);
            interface1.addMethod(method);
        }

        final GeneratedJavaFile file = new GeneratedJavaFile(interface1, project);
        files.add(file);
    }

    /**
     * 添加实现类
     * @param introspectedTable
     * @param tableName
     * @param files
     */
    protected void addServiceImpl(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable, final String tableName,
        final List<GeneratedJavaFile> files) {

        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        if (introspectedTable.getRules().generatePrimaryKeyClass()) {
            topLevelClass.addImportedType(pojoPrimaryType);
        }
        StringBuilder sb = new StringBuilder();

        topLevelClass.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @ClassName: "); //$NON-NLS-1$
        sb.append(topLevelClass.getType().getShortName());
        topLevelClass.addJavaDocLine(sb.toString()); //$NON-NLS-1$

        sb = new StringBuilder();
        sb.append(" * @Description: ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append("表对应dao操作接口实现类");
        topLevelClass.addJavaDocLine(sb.toString());

        sb = new StringBuilder();
        sb.append(" * @author: Interests");
        topLevelClass.addJavaDocLine(sb.toString());

        topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$

        if (introspectedTable.getRules().generatePrimaryKeyClass()) {
            topLevelClass.addImportedType(pojoPrimaryType);
        }

        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        // 设置实现的接口
        // topLevelClass.addSuperInterface(interfaceType);

        if (enableAnnotation) {
            topLevelClass.addAnnotation("@Repository");
            topLevelClass.addImportedType(service);
        }

        // topLevelClass.setSuperClass("ITableDao");

        addLogger(topLevelClass);

        // 添加引用dao
        addField(topLevelClass, tableName);
        // 添加方法
        Method method = countByExample(introspectedTable, tableName);
        context.getPlugins().clientCountByExampleMethodGenerated(method, topLevelClass, introspectedTable);
        topLevelClass.addMethod(method);

        boolean hasPrimaryKey = introspectedTable.hasPrimaryKeyColumns();
        if (hasPrimaryKey) {
            method = selectByPrimaryKey(introspectedTable, tableName);
            context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, topLevelClass, introspectedTable);
            topLevelClass.addMethod(method);
        }
        method = selectByExample(introspectedTable, tableName);
        context.getPlugins().clientSelectByExampleWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable);
        topLevelClass.addMethod(method);

        /**
         * type 的意义 pojo 1 ;key 2 ;example 3 ;pojo+example 4
         */
        if (enableDeleteByPrimaryKey && hasPrimaryKey) {
            method = getOtherInteger("deleteByPrimaryKey", introspectedTable, tableName, 2);
            context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(method, topLevelClass, introspectedTable);
            topLevelClass.addMethod(method);
        }
        if (enableUpdateByPrimaryKeySelective && hasPrimaryKey) {
            method = getOtherInteger("updateByPrimaryKeySelective", introspectedTable, tableName, 1);
            context.getPlugins().clientUpdateByPrimaryKeySelectiveMethodGenerated(method, topLevelClass, introspectedTable);
            topLevelClass.addMethod(method);
        }
        if (enableUpdateByPrimaryKey && hasPrimaryKey) {
            method = getOtherInteger("updateByPrimaryKey", introspectedTable, tableName, 1);
            context.getPlugins().clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable);
            topLevelClass.addMethod(method);
        }
        if (enableDeleteByExample) {
            method = getOtherInteger("deleteByExample", introspectedTable, tableName, 3);
            context.getPlugins().clientDeleteByExampleMethodGenerated(method, topLevelClass, introspectedTable);
            topLevelClass.addMethod(method);
        }
        if (enableUpdateByExampleSelective) {
            method = getOtherInteger("updateByExampleSelective", introspectedTable, tableName, 4);
            context.getPlugins().clientUpdateByExampleSelectiveMethodGenerated(method, topLevelClass, introspectedTable);
            topLevelClass.addMethod(method);
        }
        if (enableUpdateByExample) {
            method = getOtherInteger("updateByExample", introspectedTable, tableName, 4);
            context.getPlugins().clientUpdateByExampleWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable);
            topLevelClass.addMethod(method);
        }
        if (enableInsert) {
            method = getOtherInsertboolean("insert", introspectedTable, tableName);
            context.getPlugins().clientInsertMethodGenerated(method, topLevelClass, introspectedTable);
            topLevelClass.addMethod(method);
        }
        if (enableInsertSelective) {
            method = getOtherInsertboolean("insertSelective", introspectedTable, tableName);
            context.getPlugins().clientInsertSelectiveMethodGenerated(method, topLevelClass, introspectedTable);
            topLevelClass.addMethod(method);
        }
        // 生成文件
        final GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass, project);
        files.add(file);
    }

    /**
     * 添加字段
     * @param topLevelClass
     */
    protected void addField(final TopLevelClass topLevelClass, final String tableName) {
        // 添加 dao
        final Field field = new Field();
        field.setName(toLowerCase(daoType.getShortName())); // 设置变量名
        topLevelClass.addImportedType(daoType);
        field.setType(daoType); // 类型
        field.setVisibility(JavaVisibility.PRIVATE);
        if (enableAnnotation) {
            field.addAnnotation("@Resource");
        }

        final StringBuilder sb = new StringBuilder();

        field.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Fields " + tableName + "表的Mybatis Mapper操作映射类");
        field.addJavaDocLine(sb.toString());

        field.addJavaDocLine(" */"); //$NON-NLS-1$
        topLevelClass.addField(field);
    }

    /**
     * 添加方法
     */
    protected Method selectByPrimaryKey(final IntrospectedTable introspectedTable, final String tableName) {
        final Method method = new Method();
        method.setName("selectByPrimaryKey");
        method.setReturnType(pojoType);
        if (introspectedTable.getRules().generatePrimaryKeyClass()) {
            final FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
            method.addParameter(new Parameter(type, "key"));
        } else {
            for (final IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
                final FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
                method.addParameter(new Parameter(type, introspectedColumn.getJavaProperty()));
            }
        }
        method.setVisibility(JavaVisibility.PUBLIC);
        final StringBuilder sb = new StringBuilder();
        // method.addBodyLine("try {");
        sb.append("return this.");
        sb.append(getDaoShort());
        sb.append("selectByPrimaryKey");
        sb.append("(");
        if (introspectedTable.getRules().generatePrimaryKeyClass()) {
            sb.append("key");
        } else {
            for (final IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append(");");
        method.addBodyLine(sb.toString());
        // method.addBodyLine("} catch (Exception e) {");
        // method.addBodyLine("logger.error(\"Exception: \", e);");
        // method.addBodyLine("return null;");
        // method.addBodyLine("}");
        // method.addAnnotation("@Override");
        return method;
    }

    /**
     * 添加方法
     */
    protected Method countByExample(final IntrospectedTable introspectedTable, final String tableName) {
        final Method method = new Method();
        method.setName("countByExample");
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.addParameter(new Parameter(pojoCriteriaType, "example"));
        method.setVisibility(JavaVisibility.PUBLIC);
        final StringBuilder sb = new StringBuilder();
        sb.append("int count = this.");
        sb.append(getDaoShort());
        sb.append("countByExample");
        sb.append("(");
        sb.append("example");
        sb.append(");");
        method.addBodyLine(sb.toString());
        method.addBodyLine("LOGGER.debug(\"count: {}\", count);");
        method.addBodyLine("return count;");
        // method.addAnnotation("@Override");
        return method;
    }

    /**
     * 添加方法
     */
    protected Method selectByExample(final IntrospectedTable introspectedTable, final String tableName) {
        final Method method = new Method();
        method.setName("selectByExample");
        method.setReturnType(new FullyQualifiedJavaType("List<" + tableName + ">"));
        method.addParameter(new Parameter(pojoCriteriaType, "example"));
        method.setVisibility(JavaVisibility.PUBLIC);
        final StringBuilder sb = new StringBuilder();
        sb.append("return this.");
        sb.append(getDaoShort());
        if (introspectedTable.hasBLOBColumns()) {
            sb.append("selectByExampleWithBLOBs");
        } else {
            sb.append("selectByExample");
        }
        sb.append("(");
        sb.append("example");
        sb.append(");");
        method.addBodyLine(sb.toString());
        // method.addAnnotation("@Override");
        return method;
    }

    /**
     * 添加方法
     */
    protected Method getOtherInteger(final String methodName, final IntrospectedTable introspectedTable, final String tableName, final int type) {
        final Method method = new Method();
        method.setName(methodName);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        final String params = addParams(introspectedTable, method, type);
        method.setVisibility(JavaVisibility.PUBLIC);
        final StringBuilder sb = new StringBuilder();
        // method.addBodyLine("try {");
        sb.append("return this.");
        sb.append(getDaoShort());
        if (introspectedTable.hasBLOBColumns() && !"updateByPrimaryKeySelective".equals(methodName) && !"deleteByPrimaryKey".equals(methodName)
            && !"deleteByExample".equals(methodName) && !"updateByExampleSelective".equals(methodName)) {
            sb.append(methodName + "WithBLOBs");
        } else {
            sb.append(methodName);
        }
        sb.append("(");
        sb.append(params);
        sb.append(");");
        method.addBodyLine(sb.toString());
        // method.addAnnotation("@Override");
        return method;
    }

    /**
     * 添加方法
     */
    protected Method getOtherInsertboolean(final String methodName, final IntrospectedTable introspectedTable, final String tableName) {
        final Method method = new Method();
        method.setName(methodName);
        method.setReturnType(returnType);
        method.addParameter(new Parameter(pojoType, "record"));
        method.setVisibility(JavaVisibility.PUBLIC);
        final StringBuilder sb = new StringBuilder();
        if (returnType == null) {
            sb.append("this.");
        } else {
            sb.append("return this.");
        }
        sb.append(getDaoShort());
        sb.append(methodName);
        sb.append("(");
        sb.append("record");
        sb.append(");");
        method.addBodyLine(sb.toString());
        // method.addAnnotation("@Override");
        return method;
    }

    /**
     * type 的意义 pojo 1 key 2 example 3 pojo+example 4
     */
    protected String addParams(final IntrospectedTable introspectedTable, final Method method, final int type1) {
        switch (type1) {
            case 1:
                method.addParameter(new Parameter(pojoType, "record"));
                return "record";
            case 2:
                final StringBuffer sb = new StringBuffer();
                if (introspectedTable.getRules().generatePrimaryKeyClass()) {
                    final FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
                    method.addParameter(new Parameter(type, "key"));
                    sb.append("key");
                } else {

                    for (final IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
                        sb.append(introspectedColumn.getJavaProperty());
                        sb.append(",");
                        final FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
                        method.addParameter(new Parameter(type, introspectedColumn.getJavaProperty()));
                        sb.setLength(sb.length() - 1);
                    }
                }

                return sb.toString();
            case 3:
                method.addParameter(new Parameter(pojoCriteriaType, "example"));
                return "example";
            case 4:

                method.addParameter(0, new Parameter(pojoType, "record"));
                method.addParameter(1, new Parameter(pojoCriteriaType, "example"));
                if (method.getName().equals("updateByExampleSelective") || method.getName().equals("updateByExample")) {
                    return "record, example";
                }
                return "record, example";
            default:
                break;
        }
        return null;
    }

    protected void addComment(final JavaElement field, String comment) {
        final StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        comment = comment.replaceAll(OutputUtilities.lineSeparator, "<br>" + OutputUtilities.lineSeparator + "\t * ");
        sb.append(comment);
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
    }

    /**
     * 添加字段
     * @param topLevelClass
     */
    protected void addField(final TopLevelClass topLevelClass) {
        // 添加 success
        Field field = new Field();
        field.setName("success"); // 设置变量名
        field.setType(FullyQualifiedJavaType.getBooleanPrimitiveInstance()); // 类型
        field.setVisibility(JavaVisibility.PRIVATE);
        addComment(field, "执行结果");
        topLevelClass.addField(field);
        // 设置结果
        field = new Field();
        field.setName("message"); // 设置变量名
        field.setType(FullyQualifiedJavaType.getStringInstance()); // 类型
        field.setVisibility(JavaVisibility.PRIVATE);
        addComment(field, "消息结果");
        topLevelClass.addField(field);
    }

    /**
     * 添加方法
     */
    protected void addMethod(final TopLevelClass topLevelClass) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("setSuccess");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getBooleanPrimitiveInstance(), "success"));
        method.addBodyLine("this.success = success;");
        topLevelClass.addMethod(method);

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        method.setName("isSuccess");
        method.addBodyLine("return success;");
        topLevelClass.addMethod(method);

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("setMessage");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "message"));
        method.addBodyLine("this.message = message;");
        topLevelClass.addMethod(method);

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
        method.setName("getMessage");
        method.addBodyLine("return message;");
        topLevelClass.addMethod(method);
    }

    /**
     * 添加方法
     */
    protected void addMethod(final TopLevelClass topLevelClass, final String tableName) {
        Method method2 = new Method();
        for (int i = 0; i < methods.size(); i++) {
            Method method = new Method();
            method2 = methods.get(i);
            method = method2;
            method.removeAllBodyLines();
            method.removeAnnotation();
            final StringBuilder sb = new StringBuilder();
            sb.append("return this.");
            sb.append(getDaoShort());
            sb.append(method.getName());
            sb.append("(");
            final List<Parameter> list = method.getParameters();
            for (int j = 0; j < list.size(); j++) {
                sb.append(list.get(j).getName());
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
            sb.append(");");
            method.addBodyLine(sb.toString());
            topLevelClass.addMethod(method);
        }
        methods.clear();
    }

    /**
     * BaseUsers to baseUsers
     * @param tableName
     * @return
     */
    protected String toLowerCase(final String tableName) {
        final StringBuilder sb = new StringBuilder(tableName);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }

    /**
     * BaseUsers to baseUsers
     * @param tableName
     * @return
     */
    protected String toUpperCase(final String tableName) {
        final StringBuilder sb = new StringBuilder(tableName);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    /**
     * 导入需要的类
     */
    private void addImport(final Interface interfaces, final TopLevelClass topLevelClass) {
        interfaces.addImportedType(pojoType);
        interfaces.addImportedType(pojoCriteriaType);
        interfaces.addImportedType(listType);
        topLevelClass.addImportedType(daoType);
        // topLevelClass.addImportedType(interfaceType);
        topLevelClass.addImportedType(pojoType);
        topLevelClass.addImportedType(pojoCriteriaType);
        topLevelClass.addImportedType(listType);
        topLevelClass.addImportedType(slf4jLogger);
        topLevelClass.addImportedType(slf4jLoggerFactory);
        if (enableAnnotation) {
            topLevelClass.addImportedType(service);
            topLevelClass.addImportedType(autowired);
        }
    }

    /**
     * 导入logger
     */
    private void addLogger(final TopLevelClass topLevelClass) {
        final Field field = new Field();
        field.setFinal(true);
        field.setInitializationString("LoggerFactory.getLogger(" + topLevelClass.getType().getShortName() + ".class)"); // 设置值
        field.setName("LOGGER"); // 设置变量名
        field.setStatic(true);
        field.setType(new FullyQualifiedJavaType("Logger")); // 类型
        field.setVisibility(JavaVisibility.PRIVATE);
        final StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Fields LOGGER : 日志操作类"); //$NON-NLS-1$
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */"); //$NON-NLS-1$
        topLevelClass.addField(field);
    }

    private String getDaoShort() {
        return toLowerCase(daoType.getShortName()) + ".";
    }

    @Override
    public boolean clientInsertMethodGenerated(final Method method, final Interface interfaze, final IntrospectedTable introspectedTable) {
        returnType = method.getReturnType();
        return true;
    }
}
