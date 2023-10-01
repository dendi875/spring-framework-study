package com.zq.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *
 *  *  "Constructor" Autowiring 依赖 Setter 方法注入示例
 *  *
 *  *  56丨构造器依赖注入：官方为什么推荐使用构造器注入？.mp4
 *  *
 *  *  PPT: 第六章 Spring 依赖注入.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 21:22:46
 */
public class AutoWiringByConstructorDependencySetterInjectionDemo {

	public static void main(String[] args) {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		String xmlResourcePath = "classpath:/META-INF/autowiring-dependency-constructor-injection.xml";
		// 加载 XML 资源，解析并且生成 BeanDefinition
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
		// 依赖查找并且创建 Bean
		UserHolder userHolder = beanFactory.getBean(UserHolder.class);
		System.out.println(userHolder);

	}

}
