package com.zq.dependency.lookup;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 安全依赖查找
 *
 * 视频：
 * 47丨安全依赖查找.mp4
 *
 * PPT：
 * 第五章 Spring 依赖查找.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 16:05:09
 */
public class TypeSafetyDependencyLookupDemo {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 将当前类 TypeSafetyDependencyLookupDemo 作为配置类（Configuration Class）
		applicationContext.register(TypeSafetyDependencyLookupDemo.class);
		// 启动应用上下文
		applicationContext.refresh();

		// 演示 BeanFactory#getBean 方法的安全性
		displayBeanFactoryGetBean(applicationContext);
		// 演示 ObjectFactory#getObject 方法的安全性
		displayObjectFactoryGetObject(applicationContext);
		// 演示 ObjectProvider#getIfAvaiable 方法的安全性
		displayObjectProviderIfAvailable(applicationContext);

		// 演示 ListableBeanFactory#getBeansOfType 方法的安全性
		displayListableBeanFactoryGetBeansOfType(applicationContext);
		// 演示 ObjectProvider Stream 操作的安全性
		displayObjectProviderStreamOps(applicationContext);

		// 关闭应用上下文
		applicationContext.close();
	}

	/**
	 * 集合类型查找  ObjectProvider#stream 是安全的
	 */
	private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
		ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
		printBeansException("displayObjectProviderStreamOps", () -> userObjectProvider.forEach(System.out::println));
	}

	/**
	 * 集合类型查找 ListableBeanFactory#getBeansOfType 是安全的
	 */
	private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
		printBeansException("displayListableBeanFactoryGetBeansOfType", () -> beanFactory.getBeansOfType(User.class));
	}

	/**
	 * 单一类型查找 ObjectProvider#getIfAvailable 是安全的
	 */
	private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
		ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
		printBeansException("displayObjectProviderIfAvailable", () -> userObjectProvider.getIfAvailable());
	}

	/**
	 * 单一类型查找 ObjectFactory#getObject 是不安全的
	 */
	private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
		// ObjectProvider is ObjectFactory
		ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
		printBeansException("displayObjectFactoryGetObject", () -> userObjectFactory.getObject());
	}

	/**
	 * 单一类型查找 BeanFactory#getBean 是不安全的
	 */
	public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
		printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
	}

	private static void printBeansException(String source, Runnable runnable) {
		System.err.println("==========================================");
		System.err.println("Source from :" + source);
		try {
			runnable.run();
		} catch (BeansException exception) {
			exception.printStackTrace();
		}
	}
}
