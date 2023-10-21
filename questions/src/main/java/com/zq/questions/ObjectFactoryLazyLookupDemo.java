package com.zq.questions;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 *
 * 视频：加餐1丨为什么说ObjectFactory提供的是延迟依赖查找.mp4
 *
 * 为什么说 ObjectFactory 提供的是延迟依赖查找？
 *
 * 原因：
 * 1. ObjectFactory（或 ObjectProvider）可以关联某一类型 Bean
 * 2. ObjectFactory 和 ObjectProvider 对象在被依赖注入和依赖查询时并未实时查找关联类型的 Bean
 * 3. 当 ObjectFactory（或 ObjectProvider）调用 getObject() 方法时，目标 Bean 才被依赖查找
 *
 * 总结：ObjectFactory (或 ObjectProvider) 相当于某一类型 Bean 依赖查找的代理对象
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-21 15:33:43
 */
public class ObjectFactoryLazyLookupDemo {

	@Autowired
	private ObjectFactory<User> userObjectFactory;

	@Autowired
	private ObjectProvider<User> userObjectProvider;


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册引导的 Configuration Class
		context.register(ObjectFactoryLazyLookupDemo.class);

		// 刷新 Spring 应用上下文
		context.refresh();

		// 依赖查找配置类
		ObjectFactoryLazyLookupDemo lazyLookupDemo = context.getBean(ObjectFactoryLazyLookupDemo.class);

		// 相当于某一类型 Bean 依赖查找的代理对象
		ObjectFactory<User> userObjectFactory = lazyLookupDemo.userObjectFactory;
		ObjectProvider<User> userObjectProvider = lazyLookupDemo.userObjectProvider;

		// 看一下 userObjectFactory 与 userObjectProvider 是否是同一个对象
		// 看一下 userObjectFactory 与 userObjectProvider 类型是否相同
		System.out.println("userObjectFactory == userObjectProvider: " + (userObjectFactory == userObjectProvider));
		System.out.println("userObjectFactory.getClass() == userObjectProvider.getClass() : "
				+ (userObjectFactory.getClass() == userObjectProvider.getClass())
		);

		// 延时查找：调用 getObject() 方法时，目标 Bean 才被依赖查找
		System.out.println("user = " + userObjectFactory.getObject());
		System.out.println("user = " + userObjectProvider.getObject());
		System.out.println("user = " + context.getBean(User.class));

		// 关闭 Spring 应用上下文
		context.close();
	}


	@Bean
	@Lazy
	public static User user() {
		User user = new User();
		user.setId(1L);
		user.setName("张权");
		return user;
	}
}
