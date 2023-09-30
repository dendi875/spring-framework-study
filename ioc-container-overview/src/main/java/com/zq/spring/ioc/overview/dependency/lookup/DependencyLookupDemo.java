package com.zq.spring.ioc.overview.dependency.lookup;

import com.zq.spring.ioc.overview.annotation.Super;
import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Spring IoC 依赖查找方式
 *
 * 1. 通过 Bean 名称查找
 * 	1.1 实时查找
 * 	1.2 延时查找
 * 2. 通过 Bean 类型查找
 *  2.1 单个 Bean 对象
 *  2.2 集合 Bean 对象
 * 3. 通过 Java 注解查找
 *  3.1 单个 Bean 对象
 *  3.2 集合 Bean 对象
 *
 *  @author <quanzhang875@gmail.com>
 *  @since  2023-08-31 16:07:33
 */
public class DependencyLookupDemo {

	public static void main(String[] args) {
		// 配置 XML 配置文件
		// 启用 Spring 应用上下文
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

		lookupRealTime(beanFactory);
		lookupInLazy(beanFactory);

		lookupByType(beanFactory);
		lookupCollectionByType(beanFactory);

		lookupByAnnotationType(beanFactory);
	}

	/**
	 * 通过 Java 注解查找
	 */
	private static void lookupByAnnotationType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
			System.out.println("通过注解查找所有标注 @Super 的 User 集合对象: " + users);
		}
	}

	/**
	 * 通过 Bean 类型查找--集合Bean对象
	 */
	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("通过 Bean 类型查找--集合Bean对象: " + users);
		}
	}

	/**
	 * 通过 Bean 类型查找--单个Bean对象
	 */
	private static void lookupByType(BeanFactory beanFactory) {
		User user = beanFactory.getBean(User.class);
		System.out.println("通过 Bean 类型查找--单个Bean对象: " + user);
	}

	/**
	 * 通过 Bean 名称查找--延时查找
	 */
	private static void lookupInLazy(BeanFactory beanFactory) {
		ObjectFactory<User> objectFactory = (ObjectFactory<User>)beanFactory.getBean("objectFactory");
		User user = objectFactory.getObject();
		System.out.println("通过 Bean 名称查找--延时查找: " + user);
	}

	/**
	 * 通过 Bean 名称查找--实时查找
	 */
	private static void lookupRealTime(BeanFactory beanFactory) {
		User user = (User) beanFactory.getBean("user");
		System.out.println("通过 Bean 名称查找--实时查找: " + user);
	}
}
