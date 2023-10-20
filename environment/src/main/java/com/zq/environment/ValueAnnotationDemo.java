package com.zq.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 视频：221丨依赖注入@Value.mp4
 * PPT: 第十九章 Spring Environment 抽象（Environment Abstraction）.pdf
 *
 * 依赖注入 @Value
 * • 通过注入 @Value
 * 		• 实现 - org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-20 13:57:59
 */
public class ValueAnnotationDemo {

	@Value("${user.name}")
	private String name;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		context.register(ValueAnnotationDemo.class);

		// 启动 Spring 应该上下文
		context.refresh();

		// 依赖查找 Bean
		ValueAnnotationDemo valueAnnotationDemo = context.getBean(ValueAnnotationDemo.class);

		System.out.println(valueAnnotationDemo.name);

		// 关闭 Spring 应用上下文
		context.close();
	}
}
