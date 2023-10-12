package com.zq.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * {@link ExtensibleXmlAuthoringDemo}
 *
 * "users.xsd" {@link NamespaceHandler} 实现
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-12 16:30:35
 */
public class UsersNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		// 将 "user" 元素注册对应的 BeanDefinitionParser 实现
		registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
	}
}
