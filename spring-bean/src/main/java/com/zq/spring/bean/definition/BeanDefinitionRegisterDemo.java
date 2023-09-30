package com.zq.spring.bean.definition;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 35丨注册Spring Bean：如何将BeanDefinition注册到IoC容器？.mp4
 *
 * BeanDefinition 注册有三种方式：
 * 一种：XML 配置元信息，不常用
 * 	<bean name="" ... />
 *
 * 二种：Java 注解方式配置元信息
 * 1. @Bean
 * 2. @Component
 * 2. @Import
 *
 * 三种：Java API 配置元信息
 * 1. 命名方式：BeanDefinitionRegistry#registerBeanDefinition(String,BeanDefinition)
 * 2. 非命名方式：BeanDefinitionReaderUtils#registerWithGeneratedName(AbstractBeanDefinition,BeanDefinitionRegistry)
 * 3. 配置类方式：AnnotatedBeanDefinitionReader#register(Class...)
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-04 16:08:28
 */

/**
 * 第二种方式中的： Java @Import 注解方式配置元信息
 */
@Import(BeanDefinitionRegisterDemo.Config.class)
public class BeanDefinitionRegisterDemo {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册配置类
		// 第三种方式中的： 配置类方式
		applicationContext.register(BeanDefinitionRegisterDemo.class);

		// 启动 Spring 上下文
		applicationContext.refresh();

		// 命名方式
		registerBeanDefinition(applicationContext);
		// 非命名方式
		registerWithGeneratedName(applicationContext);

		// 按照类型进行查找
		System.out.println("查找 Config 类型的所有 Beans: " + applicationContext.getBeansOfType(Config.class));
		System.out.println("查找 User 类型的所有 Beans: " + applicationContext.getBeansOfType(User.class));

		// 显示关闭 Spring 上下文
		applicationContext.close();
	}

	/**
	 * 第二种方式中的：Java @Component 注解方式配置元信息
	 */
	@Component
	public static class Config {
		/**
		 * 第二种方式中的：Java @Bean 注解方式配置元信息
		 */
		@Bean(name = {"user", "zqUser"})
		public User user() {
			User user = new User();
			user.setId(1L);
			user.setName("小张1");
			return user;
		}
	}

	/**
	 * 第三种方式 Java API 配置元信息中的：命名方式
	 */
	private static void registerBeanDefinition(BeanDefinitionRegistry registry) {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		beanDefinitionBuilder
				.addPropertyValue("id", 1L)
				.addPropertyValue("name", "命名方式小张");

		registry.registerBeanDefinition("zqUser2", beanDefinitionBuilder.getBeanDefinition());
	}

	/**
	 * 第三种方式 Java API 配置元信息中的：非命名方式
	 */
	private static void  registerWithGeneratedName(BeanDefinitionRegistry registry) {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		beanDefinitionBuilder
				.addPropertyValue("id", 1L)
				.addPropertyValue("name", "非命名方式小张");

		BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
	}
}
