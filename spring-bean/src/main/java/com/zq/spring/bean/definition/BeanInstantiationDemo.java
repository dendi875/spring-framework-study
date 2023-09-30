package com.zq.spring.bean.definition;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 36丨实例化Spring Bean：Bean实例化的姿势有多少种？.mp4
 *
 * Bean 实例化（Instantiation）
 * 常规方式：
 * 1. 通过构造器（配置元信息：XML、Java注解和Java API）
 * 2. 通过静态方法（配置元信息：XML、Java API）
 * 3. 通过 Bean 工厂方法（配置元信息：XML、Java API）
 * 4. 通过 FactoryBean（配置元信息：XML、Java注解和Java API）
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-04 20:14:12
 */
public class BeanInstantiationDemo {

	public static void main(String[] args) {
		// 配置 XML 配置文件
		// 启动 Spring 应用上下文
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
		User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
		User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
		User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);

		System.out.println(userByStaticMethod);
		System.out.println(userByInstanceMethod);
		System.out.println(userByFactoryBean);

		System.out.println(userByStaticMethod == userByInstanceMethod);
		System.out.println(userByInstanceMethod == userByFactoryBean);
	}
}
