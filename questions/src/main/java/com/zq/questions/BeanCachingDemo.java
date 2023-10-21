package com.zq.questions;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 视频：加餐2丨依赖查找（注入）的Bean会被缓存吗？.mp4
 *
 * 依赖查找或依赖注入的 Bean 会被缓存吗？
 *
 * 1. 单例 Bean (Singleton)--会被缓存
 *  1.1. 缓存位置 {@link org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#singletonObjects}
 *
 * 2. 原型 Bean (Prototype)--不会被缓存
 * 	2.1. 当依赖查找或依赖注入时，根据 BeanDefinition 每次创建
 *
 * 3. 其它 Scope Bean
 * 	3.1. request: 每个 ServletRequest 内部缓存，生命周期维持在每次 HTTP 请求
 * 	3.2. session: 每个 HttpSession 内部缓存，生命周期维持在每个用户 HTTP 请求
 * 	3.3. application: 当前 Servlet 应用内部缓存
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-21 16:03:37
 */
public class BeanCachingDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		context.register(BeanCachingDemo.class);

		// 刷新 Spring 应用上下文
		context.refresh();

		// 验证 单例 Bean 会不会被缓存
		// 因为 BeanCachingDemo 是 Configuration Class，它是全局唯一的，它就是个单例 Bean，我们用它来验证
		BeanCachingDemo beanCachingDemo = context.getBean(BeanCachingDemo.class);
		for (int i = 0; i < 9; i++) {
			// 验证每次依赖查找时单例 Bean 会被缓存
			System.out.println(beanCachingDemo == context.getBean(BeanCachingDemo.class));
		}

		// 验证原型 Bean 会不会被缓存
		User user = context.getBean(User.class);
		for (int i = 0; i < 9; i++) {
			// 验证每次依赖注入时Prototype Bean 不会被缓存
			System.out.println(user == context.getBean(User.class));
		}

		// 关闭 Spring 应用上下文
		context.close();
	}

	@Bean
	@Scope("prototype") // 原型 Bean
	public static User user() {
		User user = new User();
		user.setId(1L);
		user.setName("张权");
		return user;
	}
}
