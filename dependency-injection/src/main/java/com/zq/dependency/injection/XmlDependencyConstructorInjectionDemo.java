package com.zq.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *
 * 基于 XML 资源的依赖 Constructor 方法注入示例
 *
 * 视频：56丨构造器依赖注入：官方为什么推荐使用构造器注入？.mp4
 *
 * PPT: 第六章 Spring 依赖注入.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 21:10:07
 */
public class XmlDependencyConstructorInjectionDemo {

	public static void main(String[] args) {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		String xmlResourcePath = "classpath:/META-INF/dependency-constructor-injection.xml";
		// 加载 XML 资源，解析并且生成 BeanDefinition
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
		// 依赖查找并且创建 Bean
		UserHolder userHolder = beanFactory.getBean(UserHolder.class);
		System.out.println(userHolder);

	}
}
