/*
 *  Copyright 2008 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * This plugin adds the java.io.Serializable marker interface to all generated
 * model objects.
 * <p>
 * This plugin demonstrates adding capabilities to generated Java artifacts, and
 * shows the proper way to add imports to a compilation unit.
 * <p>
 * Important: This is a simplistic implementation of serializable and does not
 * attempt to do any versioning of classes.
 *
 * @author Jeff Butler
 *
 */
public class SerializablePlugin extends PluginAdapter {

	private final FullyQualifiedJavaType serializable;

	public SerializablePlugin() {
		super();
		serializable = new FullyQualifiedJavaType("java.io.Serializable"); //$NON-NLS-1$

	}

	@Override
    public boolean validate(final List<String> warnings) {
		// this plugin is always valid
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	protected void makeSerializable(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(serializable);
		topLevelClass.addSuperInterface(serializable);

		final Field field = new Field();
		field.setFinal(true);
		field.setInitializationString("1L"); //$NON-NLS-1$
		field.setName("serialVersionUID"); //$NON-NLS-1$
		field.setStatic(true);
		field.setType(new FullyQualifiedJavaType("long")); //$NON-NLS-1$
		field.setVisibility(JavaVisibility.PRIVATE);
		//context.getCommentGenerator().addFieldComment(field, introspectedTable);
        final StringBuilder sb = new StringBuilder();

        field.addJavaDocLine("/**"); //$NON-NLS-1$
        sb.append(" * @Fields serialVersionUID : 自动生成默认序列化ID"); //$NON-NLS-1$
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */"); //$NON-NLS-1$

		topLevelClass.addField(0, field);
	}
}
