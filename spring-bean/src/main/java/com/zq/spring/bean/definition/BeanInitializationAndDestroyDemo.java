package com.zq.spring.bean.definition;

import com.zq.spring.bean.factory.DefaultUserFactory;
import com.zq.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 37丨初始化Spring Bean：Bean初始化有哪些方式？.mp4
 *
 * 1. 基于 @PostConstruct 注解方式
 * 2. 实现 InitializingBean 接口的 afterPropertiesSet() 方法
 * 3. 自定义初始化方法
 * 	3.1 XML 配置 <bean init-method="init" .../>
 * 	3.2 Java 注解 @Bean(initMethod = "init")
 * 	3.3 Java API AbstractBeanDefinition@setInitMethodName(Sting)
 *
 * 4. 延迟初始化 Spring Bean
 * 4.1 XML 配置 <bean lazy-init="true" ../>
 * 4.2 Java 注解 @Lazy(true)
 *
 * 39丨销毁Spring Bean： 销毁Bean的基本操作有哪些？.mp4
 *
 *  1. 基于 @PreDestroy 注解方式
 *  2. 实现 DisposableBean 接口的 destroy() 方法
 *  3. 自定义初始化方法
 *   	3.1 XML 配置 <bean destroy="destroy" .../>
 *   	3.2 Java 注解 @Bean(destroyMethod = "destroy")
 *  	3.3 Java API AbstractBeanDefinition@setDestroyMethodName(Sting)
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-13 17:31:23
 */
@Configuration // Configuration Class
public class BeanInitializationAndDestroyDemo {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class
		applicationContext.register(BeanInitializationAndDestroyDemo.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 非延迟初始化在 Spring 应用上下文启动完成之前被初始化
		System.out.println("Spring 应用上下文已经启动...");

		initAndDestroy(applicationContext);

		System.out.println("Spring 应用上下文准备关闭...");
		applicationContext.close(); // 关闭 Spring 应用上下文
		System.out.println("Spring 应用上下文已经关闭...");

	}

	/**
	 * 一、初始化：
	 * 1、基于 @PostConstruct 注解方式
	 * {@link com.zq.spring.bean.factory.DefaultUserFactory#init}
	 *
	 * 2、实现 InitializingBean 接口的 afterPropertiesSet() 方法
	 * {@link DefaultUserFactory#afterPropertiesSet}
	 *
	 * 3、自定义实现：Java 注解 @Bean(initMethod = "init")
	 * {@link DefaultUserFactory#initUserFactory}
	 *
	 * 二、延迟初始化 @Lazy(true)
	 * 当某个 Bean 定义为延迟初始化，那么，Spring 容器返回的对象与非延迟的对象存在怎样的差异?
	 * 1. 在注册上没有差异
	 * 2. 在使用上：非延迟初始化的 Bean 在启动 Spring 应用上下文之前就已经初始化好了，
	 * 而延迟初始化的 Bean 是按需加载的，也就是在使用依赖查找或依赖注入时才初始化
	 *
	 * 三、销毁
	 * 1、基于 @PreDestroy 注解方式
	 * {@link com.zq.spring.bean.factory.DefaultUserFactory#preDestroy}
	 *
	 * 2、实现 DisposableBean 接口的 destroy() 方法
	 * {@link DefaultUserFactory#destroy}
	 *
	 * 3、自定义实现：Java 注解 @Bean(destroyMethod = "destroy")
	 * {@link DefaultUserFactory#destroyUserFactory}
	 *
	 */
	public static void initAndDestroy(AnnotationConfigApplicationContext applicationContext) {
		// 依赖查找
		UserFactory userFactory = applicationContext.getBean(UserFactory.class);
		System.out.println(userFactory);
	}


	@Lazy(false)
	@Bean(initMethod = "initUserFactory", destroyMethod = "destroyUserFactory")
	public UserFactory userFactory() {
		return new DefaultUserFactory();
	}
}
