package com.zq.dependency.injection;

import com.zq.dependency.injection.annotation.UserGroup;
import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 *
 * {@link Qualifier} 限定依赖注入
 *
 * 视频：63丨限定注入：如何限定Bean名称注入？如何实现Bean逻辑分组注入？.mp4
 * PPT: 第六章 Spring 依赖注入.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-10-01 14:03:07
 */
public class QualifierAnnotationDependencyInjectionDemo {

	@Autowired
	private User user;

	@Qualifier("user")
	@Autowired
	private User namedUser;

	@Autowired
	private Collection<User> allUsers;

	@Autowired
	@Qualifier
	private Collection<User> qualifierUsers;

	@Autowired
	@UserGroup
	private Collection<User> userGroupUsers;

	@Bean
	@Qualifier // 进行逻辑分组
	public User user1() {
		return createUser(7L);
	}

	@Bean
	@Qualifier // 进行逻辑分组
	public User user2() {
		return createUser(8L);
	}

	@Bean
	@UserGroup // 进行逻辑分组
	public User user3() {
		return createUser(9L);
	}

	@Bean
	@UserGroup // 进行逻辑分组
	public User user4() {
		return createUser(10L);
	}

	public static User createUser(Long id) {
		User user = new User();
		user.setId(id);
		return user;
	}

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类），也会当作一个 Spring Bean
		applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

		// 加载 XML 资源，解析并且生成 BeanDefinition
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找前面注册的当前配置类的 Spring Bean
		QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

		System.out.println("demo.user = " + demo.user);
		System.out.println("demo.namedUser = " + demo.namedUser);
		System.out.println("demo.allUsers = " + demo.allUsers);
		System.out.println("demo.qualifierUsers = " + demo.qualifierUsers);
		System.out.println("demo.userGroupUsers = " + demo.userGroupUsers);

		// 显示地关闭 Spring 应用上下文
		applicationContext.close();
	}
}
