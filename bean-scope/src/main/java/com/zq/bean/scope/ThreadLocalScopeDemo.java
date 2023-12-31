package com.zq.bean.scope;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 自定义 Spring Bean 作用域
 *
 * 视频：85丨自定义Bean作用域：设计Bean作用域应该注意哪些原则？.mp4
 * PPT: 第八章 Spring Bean作用域（Scopes）.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-10-07 11:20:45
 */
public class ThreadLocalScopeDemo {

	@Bean
	@Scope(ThreadLocalScope.SCOPE_NAME)
	public User user() {
		return createUser();
	}

	private static User createUser() {
		User user = new User();
		user.setId(System.nanoTime());
		return user;
	}

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类） -> Spring Bean
		applicationContext.register(ThreadLocalScopeDemo.class);

		applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
			// 注册自定义 scope
			beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
		});

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		scopedBeansByLookup(applicationContext);

		// 关闭 Spring 应用上下文
		applicationContext.close();
	}

	private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {

		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(() -> {
				// user 是共享 Bean 对象
				User user = applicationContext.getBean("user", User.class);
				System.out.printf("[Thread id :%d] user = %s%n", Thread.currentThread().getId(), user);
			});

			// 启动线程
			thread.start();
			// 强制线程执行完成
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
	}
}
