package com.zq.spring.bean.definition;

import com.zq.spring.bean.factory.DefaultUserFactory;
import com.zq.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过外部单体对象来注册一个 Spring Bean
 *
 * 外部单体对象：它的生命周期不由 Spring 容器来进行管理，但可以被 Spring 容器托管
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-14 11:01:10
 */
public class SingletonBeanRegisterDemo {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 创建一个外部单体对象
		UserFactory userFactory = new DefaultUserFactory();

		// 注册外部单例对象
		SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
		singletonBeanRegistry.registerSingleton("userFactory", userFactory);

		// 启动 Spring 上下文容器
		applicationContext.refresh();

		// 通过依赖查找的方式来获取 UserFactory
		UserFactory userFactoryByLookup = applicationContext.getBean("userFactory", UserFactory.class);
		System.out.println("userFactory == userFactoryByLookup : " + (userFactory == userFactoryByLookup));

		// 关闭 Spring 上下文容器
		applicationContext.close();
	}
}
