package com.zq.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 非 Spring 容器管理对象作为依赖来源，实例
 *
 * 视频：73丨Spring容器管理和游离对象：为什么会有管理对象和游离对象？.mp4
 * PPT：第七章 Spring IoC依赖来源.pdf
 *
 * • 要素
 * 		• 注册：ConfigurableListableBeanFactory#registerResolvableDependency
 * • 限制
 * 	• 无生命周期管理
 * 	• 无法实现延迟初始化 Bean
 * 	• 无法通过依赖查找，只能用于依赖注入，并且只能用于通过类型的依赖注入，无法用于通过名称的依赖注入
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-10-01 17:23:12
 */
public class ResolvableDependencySourceDemo {

	@Autowired
	private String value;

	@PostConstruct
	public void init() {
		System.out.println(value);
	}

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class（配置类） -> Spring Bean
		applicationContext.register(ResolvableDependencySourceDemo.class);

		// 回调方式注册
		applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
			// 注册 Resolvable Dependency
			beanFactory.registerResolvableDependency(String.class, "Hello,World");
		});

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 显示地关闭 Spring 应用上下文
		applicationContext.close();
	}
}
