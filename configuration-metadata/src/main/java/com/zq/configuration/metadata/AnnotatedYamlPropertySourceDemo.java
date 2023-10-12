package com.zq.configuration.metadata;

import com.zq.spring.ioc.overview.domain.User;
import com.zq.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 *
 *  基于 注解 的 YAML 外部化配置示例
 *
 *  视频：121丨基于YAML资源装载外部化配置.mp4
 *  PPT：第十章 Spring 配置元信息（Configuration Metadata）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-12 21:02:27
 */
@PropertySource(
		name = "yamlPropertySource",
		value = "classpath:/META-INF/user.yaml",
		factory = YamlPropertySourceFactory.class
)
public class AnnotatedYamlPropertySourceDemo {

	@Bean
	public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name, @Value("${user.city}") City city) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setCity(city);
		return user;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册当前类作为 Configuration Class
		context.register(AnnotatedYamlPropertySourceDemo.class);
		// 启动 Spring 应用上下文
		context.refresh();

		// 获取 Map Yaml 对象，
		// AnnotatedYamlPropertySourceDemo#user 的属性赋值是来自 user.yaml
		User user = context.getBean("user", User.class);
		System.out.println(user);

		// 关闭 Spring 应用上下文
		context.close();
	}
}
