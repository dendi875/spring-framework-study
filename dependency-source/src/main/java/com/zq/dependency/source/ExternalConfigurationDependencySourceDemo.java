package com.zq.dependency.source;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置作为依赖来源
 *
 * 视频：77丨外部化配置作为依赖来源：@Value是如何将外部化配置注入Spring Bean的？.mp4
 * PPT: 第七章 Spring IoC依赖来源.pdf
 *
 *  • 要素
 *  	• 类型：非常规 Spring 对象依赖来源  {@link AutowiredAnnotationBeanPostProcessor#AutowiredAnnotationBeanPostProcessor}
 *  • 限制
 *  	• 无生命周期管理
 *  	• 无法实现延迟初始化 Bean
 *  	• 无法通过依赖查找
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-10-01 17:37:24
 */
@Configuration
@PropertySource(value = "META-INF/default.properties", encoding="UTF-8")
public class ExternalConfigurationDependencySourceDemo {

	@Value("${user.id:-1}")
	private Long id;

	@Value("${usr.name}")
	private String name;

	@Value("${user.resource:classpath://default.properties}")
	private Resource resource;

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类） -> Spring Bean
		applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找 ExternalConfigurationDependencySourceDemo Bean
		ExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

		System.out.println("demo.id = " + demo.id);
		System.out.println("demo.name = " + demo.name);
		System.out.println("demo.resource = " + demo.resource);

		// 显示地关闭 Spring 应用上下文
		applicationContext.close();
	}
}
