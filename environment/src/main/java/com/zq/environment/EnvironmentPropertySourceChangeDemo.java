package com.zq.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 视频： 227丨基于API扩展Spring外部化配置属性源.mp4
 * PPT: 第十九章 Spring Environment 抽象（Environment Abstraction）.pdf
 *
 * 基于 API 扩展 Spring 配置属性源
 * 		• Spring 应用上下文启动前装配 PropertySource
 * 		• Spring 应用上下文启动后装配 PropertySource
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-20 16:13:12
 */
public class EnvironmentPropertySourceChangeDemo {

	// 不具备动态更新能力，在 context.refresh() 之后，再修改不会变，
	// 像 Apollo 这种配置中心它提升了 @Value 注解的能力，让它可以在 refresh() 之后也可以修改生效
	@Value("${user.name}")
	private String userName;


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		context.register(EnvironmentPropertySourceChangeDemo.class);

		// 在 Spring 应用启动前，调用 Environment 中的 PropertySource
		ConfigurableEnvironment environment = context.getEnvironment();
		MutablePropertySources propertySources = environment.getPropertySources();
		// 动态插入 PropertySource 到 PropertySources

		Map<String, Object> map = new HashMap<>();
		map.put("user.name", "张权");
		MapPropertySource mapPropertySource = new MapPropertySource("first-property-source", map);
		propertySources.addFirst(mapPropertySource);

		// 启动 Spring 应用上下文
		context.refresh();

		// 在 Spring 应用启动后，调用 Environment 中的 PropertySource
		map.put("user.name", "张三");

		// 依赖查找方式获取 Bean
		EnvironmentPropertySourceChangeDemo environmentPropertySourceChangeDemo
				= context.getBean(EnvironmentPropertySourceChangeDemo.class);

		System.out.println(environmentPropertySourceChangeDemo.userName);

		for (PropertySource ps : propertySources) {
			System.out.printf("PropertySource(name=%s) 'user.name' 属性：%s\n", ps.getName(), ps.getProperty("user.name"));
		}

		// 关闭 Spring 应用上下文
		context.close();
	}
}
