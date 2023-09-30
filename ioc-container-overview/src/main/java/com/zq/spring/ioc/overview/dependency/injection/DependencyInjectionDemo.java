package com.zq.spring.ioc.overview.dependency.injection;

import com.zq.spring.ioc.overview.annotation.Super;
import com.zq.spring.ioc.overview.domain.User;
import com.zq.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * Spring IoC 依赖注入方式
 *
 * 1. 通过 Bean 名称注入
 * 2. 通过 Bean 类型注入
 *  2.1 单个 Bean 对象
 *  2.2 集合 Bean 对象
 * 3. 注入容器内建 Bean 对象
 * 4. 注入非 Bean 对象
 *
 * Spring IoC 依赖注入类型
 *  1. 实时注入
 *  2. 延时注入
 *
 *  @author <quanzhang875@gmail.com>
 *  @since  2023-08-31 16:07:33
 */
public class DependencyInjectionDemo {

	public static void main(String[] args) {
		// 配置 XML 配置文件
		// 启用 Spring 应用上下文，现在有两个上下文 lookup-context和 injection-context
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
		ApplicationContext  applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

		injectionByName(beanFactory);
		injectionByType(beanFactory);

		checkBeanFactory(beanFactory);
		checkObjectFactory(beanFactory);

		dependencySource(beanFactory);

		whoIsIoCContainer(beanFactory);
		whoIsIoCContainer(applicationContext);
	}

	/**
	 *
	 * BeanFactory 和 ApplicationContext 谁才是真正的 Spring Ioc 容器？
	 *
	 * BeanFactory 是一个底层的 IoC 容器，ApplicationContext 是在 BeanFactory 的基础上增加了一些应用特性
	 *
	 * BeanFactory 提供了一些基础功能，ApplicationContext 提供了一些企业级的更多功能
	 */
	private static void whoIsIoCContainer(BeanFactory beanFactory) {
		UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
		BeanFactory beanFactoryInter = userRepository.getBeanFactory();
		System.out.println(beanFactory == beanFactoryInter);
	}

	/**
	 *
	 * BeanFactory 和 ApplicationContext 谁才是真正的 Spring Ioc 容器？
	 *
	 * BeanFactory 是一个底层的 IoC 容器，ApplicationContext 是在 BeanFactory 的基础上增加了一些特性
	 *
	 * BeanFactory 提供了一些基础功能，ApplicationContext 提供了一些企业级的更多功能
	 */
	private static void whoIsIoCContainer(ApplicationContext applicationContext) {
		UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);
		BeanFactory beanFactoryInter = userRepository.getBeanFactory();
		System.out.println(applicationContext == beanFactoryInter);
	}

	/**
	 * Spring IoC 依赖来源
	 */
	private static void dependencySource(BeanFactory beanFactory) {
		// 依赖来源一：自定义 Bean
		UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
		System.out.println("自定义 Bean: " + userRepository.getUsers());
		// 依赖来源二：依赖注入（自定义 Bean内建的依赖）
		BeanFactory beanFactoryInner = userRepository.getBeanFactory();
		System.out.println("依赖注入（自定义 Bean内建的依赖）: " + beanFactoryInner);
		// 依赖来源三：容器内建 Bean（容器初始化时建的）
		Environment environment = beanFactory.getBean(Environment.class);
		System.out.println("容器内建 Bean（容器初始化时建的）: " +environment);
	}

	private static void checkObjectFactory(BeanFactory beanFactory) {
		UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
		ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
		System.out.println(objectFactory.getObject());
		System.out.println(objectFactory.getObject() == beanFactory);
	}

	/**
	 * 验证注入的内建 beanFactory 和外面的 beanFactory 是否是同一个
	 */
	private static void checkBeanFactory(BeanFactory beanFactory) {
		// 这里就说明注入的 beanFactoryInner 是一个内建的 BeanFactory Bean 对象，
		// 而且beanFactoryInner它是一个自定义BeanFactory Bean 的话可以通过 getBean 类型方式来查找
		UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
		BeanFactory beanFactoryInner = userRepository.getBeanFactory(); // 这是通过依赖注入方式获取

		System.out.println(beanFactoryInner);
		System.out.println(beanFactory == beanFactoryInner);
		// System.out.println(beanFactory.getBean(BeanFactory.class)); // 报错，这是通过依赖查找方式获取所以出错，只能通过依赖注入方式获取
	}

	/**
	 * 通过 Bean 名称注入
	 */
	private static void injectionByName(BeanFactory beanFactory) {
		UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
		System.out.println("通过 Bean 名称注入: " + userRepository.getUsers());
	}

	/**
	 * 通过 Bean 类型注入--集合 Bean 对象
	 */
	private static void injectionByType(BeanFactory beanFactory) {
		UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
		System.out.println("通过 Bean 类型注入--集合 Bean 对象: " + userRepository.getUsers());
	}
}
