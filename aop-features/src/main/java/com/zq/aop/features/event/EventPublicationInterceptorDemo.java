package com.zq.aop.features.event;

import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.EventPublicationInterceptor;

import java.lang.reflect.Method;

/**
 * 视频：108丨SpringAOP在Spring事件（Events）.mp4
 *
 * Spring AOP 在 Spring 事件（Events）
 * • 核心 API - org.springframework.context.event.EventPublicationInterceptor
 * • 特性描述
 * 	当 Spring AOP 代理 Bean 中的 JoinPoint 方法执行后，Spring ApplicationContext 将发布一个自定义事件（ApplicationEvent 子类）
 * • 使用限制
 * 		• EventPublicationInterceptor 关联的 ApplicationEvent 子类必须存在单参数的构造器
 * 		• EventPublicationInterceptor 需要被声明为 Spring Bean
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-27 13:40:07
 */
@EnableAspectJAutoProxy // 启用Spring AOP 注解自动代理功能
@Configuration
public class EventPublicationInterceptorDemo {

	public static void main(String[] args) {
		// 创建 Spring 应用上下文
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class，将 Executor 和 StaticExecutor 注入进来
		context.register(EventPublicationInterceptorDemo.class, Executor.class, StaticExecutor.class);

		// 刷新 Spring 应用上下文
		context.refresh();

		// 依赖查找获取Bean
		StaticExecutor staticExecutor = context.getBean(StaticExecutor.class);
		// 静态方式实现：当方法调用时发布事件并监听处理
		staticExecutor.execute();
		// 通过AOP实现：当方法调用时发布事件并监听处理
		Executor executor = context.getBean(Executor.class);
		executor.execute();

		// 关闭 Spring 应用上下文
		context.close();
	}

	// 1. 将 EventPublicationInterceptor 声明为一个 Spring Bean
	@Bean
	public EventPublicationInterceptor eventPublicationInterceptor() {
		EventPublicationInterceptor eventPublicationInterceptor = new EventPublicationInterceptor();
		// 关联事件类型
		eventPublicationInterceptor.setApplicationEventClass(ExecutedEvent.class);
		return eventPublicationInterceptor;
	}

	// 2. 实现 Pointcut Bean，去筛选出我们需要的Joint Point
	@Bean
	public Pointcut pointcut() {
		return new StaticMethodMatcherPointcut() {

			@Override
			public boolean matches(Method method, Class<?> targetClass) {
				return "execute".equals(method.getName()) && Executor.class.equals(targetClass);
			}
		};
	}

	// 3. 声明一个 Advisor Bean，
	// 参数 pointcut 和 eventPublicationInterceptor 会自动注入进来，因为前面声明了 Bean，看 @Configuration
	@Bean
	public PointcutAdvisor pointcutAdvisor(Pointcut pointcut, EventPublicationInterceptor eventPublicationInterceptor) {
		// eventPublicationInterceptor 是一个 MethodInterceptor
		// MethodInterceptor 是一个 Advice
		// 所以 eventPublicationInterceptor 也是 Advice
		return new DefaultPointcutAdvisor(pointcut, eventPublicationInterceptor);
	}

	// 监听事件
	@EventListener(ExecutedEvent.class)
	public void executed(ExecutedEvent event) {
		System.out.println("Executed: " + event);
	}
}
