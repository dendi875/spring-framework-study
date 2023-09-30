package com.zq.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *
 *  *  "byName" Autowiring 依赖 Setter 方法注入示例
 *  *
 *  *  视频：55丨Setter方法依赖注入：Setter注入的原理是什么？.mp4
 *  *
 *  *  PPT: 第六章 Spring 依赖注入.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 21:22:46
 */
public class AutoWiringByNameDependencySetterInjectionDemo {

	public static void main(String[] args) {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		String xmlResourcePath = "classpath:/META-INF/autowiring-dependency-setter-injection.xml";
		// 加载 XML 资源，解析并且生成 BeanDefinition
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
		// 依赖查找并且创建 Bean
		UserHolder userHolder = beanFactory.getBean(UserHolder.class);
		System.out.println(userHolder);

	}

}
