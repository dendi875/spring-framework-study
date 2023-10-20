package com.zq.environment;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 视频：216丨Environment占位符处理.mp4
 * PPT: 第十九章 Spring Environment 抽象（Environment Abstraction）.pdf
 *
 * Environment 占位符处理
 * • Spring 3.1 前占位符处理
 * 		• 组件：org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
 * 		• 接口：org.springframework.util.StringValueResolver
 * • Spring 3.1 + 占位符处理
 * 		• 组件：org.springframework.context.support.PropertySourcesPlaceholderConfigurer
 * 		• 接口：org.springframework.beans.factory.config.EmbeddedValueResolver
 *
 *
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-20 11:26:37
 */
public class PropertyPlaceholderConfigurerDemo {

	public static void main(String[] args) {

		// 创建并启用 Spring 应用上下文
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:/META-INF/placeholders-resolver.xml");

		// 依赖查找 Bean
		User user = context.getBean("user", User.class);
		System.out.println(user);

		// 关闭 Spring 应用上下文
		context.close();
	}
}
