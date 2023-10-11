package com.zq.configuration.metadata;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * Spring 配置元信息--基于 Java 注解装载 Spring IoC 容器配置元信息
 *
 * 视频：117丨基于Java注解装载Spring IoC容器配置元信息.mp4
 * PPT：第十章 Spring 配置元信息（Configuration Metadata）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-11 21:31:55
 */

// 将当前类即 AnnotatedSpringIoCContainerMetadataConfigurationDemo 类，作为 Configuration Class
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class) // 这就是可以把一些非 @Configuration 标记的 Class 来进行装载
@PropertySource("classpath:/META-INF/user-bean-definitions.properties") // Java 8+ @Repeatable 支持
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
// @PropertySources(@PropertySource(...))
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {


	/**
	 * user.name 是 Java Properties 默认存在，当前操作系统环境变量中的用户，而非配置文件中定义
	 * @param id
	 * @param name
	 * @return
	 */
	@Bean
	public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册当前类作为 Configuration Class
		context.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
		// 启动 Spring 应用上下文
		context.refresh();
		// beanName 和 bean 映射
		Map<String, User> usersMap = context.getBeansOfType(User.class);
		for (Map.Entry<String, User> entry : usersMap.entrySet()) {
			System.out.printf("User Bean name : %s , content : %s \n", entry.getKey(), entry.getValue());
		}
		// 关闭 Spring 应用上下文
		context.close();
	}
}
