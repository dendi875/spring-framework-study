package com.zq.configuration.metadata;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *
 * see https://docs.spring.io/spring-framework/docs/5.2.25.RELEASE/spring-framework-reference/core.html#xml-custom
 *
 * 基于 Extensible XML authoring 扩展 Spring XML 元素
 *
 * src/main/resources/META-INF/spring.schemas
 * src/main/resources/META-INF/spring.handlers
 * src/main/resources/com/zq/configuration/metadata/users.xsd
 * src/main/resources/META-INF/users-context.xml
 * com.zq.configuration.metadata.UsersNamespaceHandler
 * com.zq.configuration.metadata.UserBeanDefinitionParser
 *
 *
 * 参考：
 *
 * 视频：118丨基于Extensible XML authoring 扩展Spring XML元素.mp4
 * PPT：第十章 Spring 配置元信息（Configuration Metadata）.pdf
 *
 * Spring XML 扩展
 * 	• 编写 XML Schema 文件：定义 XML 结构
 * 	• 自定义 NamespaceHandler 实现：命名空间绑定
 * 	• 自定义 BeanDefinitionParser 实现：XML 元素与 BeanDefinition 解析
 * 	• 注册 XML 扩展：命名空间与 XML Schema 映射
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-12 15:18:21
 */
public class ExtensibleXmlAuthoringDemo {
	public static void main(String[] args) {
		// 创建 IoC 底层容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 创建 XML 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		// 加载 XML 资源
		reader.loadBeanDefinitions("META-INF/users-context.xml");
		// 获取 User Bean 对象
		User user = beanFactory.getBean(User.class);
		System.out.println(user);
	}
}
