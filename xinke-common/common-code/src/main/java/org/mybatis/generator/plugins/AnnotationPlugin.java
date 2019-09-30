package org.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;

/**
 * 给dao增加注解增加注解
 *
 * @author QQ:34847009
 * @date 2010-10-21 下午09:33:48
 */
public class AnnotationPlugin extends PluginAdapter {
	private final FullyQualifiedJavaType repository;
	private final FullyQualifiedJavaType autowired;
	private final FullyQualifiedJavaType sqlMapClient;

	public AnnotationPlugin() {
		super();
		autowired = new FullyQualifiedJavaType("javax.annotation.Resource"); //$NON-NLS-1$
		repository = new FullyQualifiedJavaType("org.springframework.stereotype.Repository"); //$NON-NLS-1$
		sqlMapClient = new FullyQualifiedJavaType("com.ibatis.sqlmap.client.SqlMapClient"); //$NON-NLS-1$
	}

	@Override
	public boolean validate(final List<String> warnings) {
		return true;
	}

	@Override
	public boolean clientGenerated(final Interface interfaze, final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
		final Method method = topLevelClass.getMethods().get(0);
		addAnnotation(topLevelClass, method);
		method.addParameter(new Parameter(sqlMapClient, "sqlMapClient"));
		method.removeBodyLine(0);
		method.addBodyLine("super.setSqlMapClient(sqlMapClient);");
		return true;
	}

	/**
	 * 添加注解
	 *
	 * @param topLevelClass
	 * @param method
	 */
	protected void addAnnotation(final TopLevelClass topLevelClass, final Method method) {
		topLevelClass.addImportedType(sqlMapClient);
		topLevelClass.addImportedType(autowired);
		topLevelClass.addImportedType(repository);
		method.addAnnotation("@Resource");
		topLevelClass.addAnnotation("@Repository");
	}

}
