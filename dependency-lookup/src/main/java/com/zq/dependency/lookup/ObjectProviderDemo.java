package com.zq.dependency.lookup;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 视频：
 * 43丨单一类型依赖查找：如何查找已知名称或类型的Bean对象？.mp4
 * 46丨延迟依赖查找：非延迟初始化Bean也能实现延迟查找？.mp4
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-14 14:19:50
 */
// @Configuration // 该注释不是必须的
public class ObjectProviderDemo {
	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 将当前类 ObjectProviderDemo 作为配置类（Configuration Class）
		applicationContext.register(ObjectProviderDemo.class);

		// 启用 Spring 应用上下文
		applicationContext.refresh();

		lookupByObjectProvider(applicationContext);
		lookupIfAvailable(applicationContext);
		lookupByStreamOps(applicationContext);

		// 关闭 Spring 应用上下文
		applicationContext.close();
	}

	@Primary
	@Bean // bean 名称默认为方法名称
	public String helloWorld() {
		return "Hello, World";
	}

	@Bean
	public String message() {
		return "Message";
	}

	private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
		ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
		System.out.println(objectProvider.getObject());
	}

	private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
		ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
		User user = userObjectProvider.getIfAvailable(User::createUser);
		System.out.println("当前 User 对象：" + user);
	}

	private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
		ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> stringIterable = objectProvider;
//        for (String string : stringIterable) {
//            System.out.println(string);
//        }
		// Stream -> Method reference
		objectProvider.stream().forEach(System.out::println);
	}


}
