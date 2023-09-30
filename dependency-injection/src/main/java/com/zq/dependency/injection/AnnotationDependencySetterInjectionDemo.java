package com.zq.dependency.injection;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 *
 *  基于 Java 注解的依赖 Setter 方法注入示例
 *
 *  视频：55丨Setter方法依赖注入：Setter注入的原理是什么？.mp4
 *
 *  PPT: 第六章 Spring 依赖注入.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 21:16:59
 */
public class AnnotationDependencySetterInjectionDemo {

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类）
		applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
		// 加载 XML 资源，解析并且生成 BeanDefinition
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找并且创建 Bean
		UserHolder userHolder = applicationContext.getBean(UserHolder.class);
		System.out.println(userHolder);

		// 显示地关闭 Spring 应用上下文
		applicationContext.close();
	}

	@Bean
	public UserHolder userHolder(User user) {
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user);
		return userHolder;
	}
}