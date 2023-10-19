package com.zq.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 视频： 210丨Spring@Enable模块驱动.mp4
 * PPT: 第十八章 Spring 注解（Annotations）.pdf
 *
 * Spring @Enable 模块驱动
 * • @Enable 模块驱动编程模式
 * • 驱动注解：@EnableXXX
 * • 导入注解：@Import 具体实现
 * • 具体实现
 * 		• 基于 Configuration Class
 * 		• 基于 ImportSelector 接口实现
 * 		• 基于 ImportBeanDefinitionRegistrar 接口实现
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 22:37:13
 */
@EnableHelloWorld
public class EnableModuleDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		context.register(EnableModuleDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		// 依赖查找 helloWorld 这个 Bean
		String helloWorld = context.getBean("helloWorld", String.class);
		System.out.println(helloWorld);

		// 关闭 Spring 应用上下文
		context.close();
	}
}
