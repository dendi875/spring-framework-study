package com.zq.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 视频：186丨基于接口的Spring事件监听器：ApplicationListener为什么选择单事件监听模式？.mp4
 * PPT: 第十七章 Spring 事件（Events）.pdf
 *
 * 事件接收者：1. 基于接口 ApplicationListener，2. 基于注解 @EventListener
 * 事件源：ApplicationContextEvent
 * 事件发布者：ApplicationEventPublisher、ApplicationEventMulticaster
 *
 * Spring 标准事件 - ApplicationEvent
 * • Java 标准事件 java.util.EventObject 扩展
 * 		• 扩展特性：事件发生事件戳
 * • Spring 应用上下文 ApplicationEvent 扩展 - ApplicationContextEvent
 * 		• Spring 应用上下文（ApplicationContext）作为事件源
 * 		• 具体实现：
 * 			• org.springframework.context.event.ContextClosedEvent      Spring 应用上下文就绪事件
 * 			• org.springframework.context.event.ContextRefreshedEvent   Spring 应用上下文启动事件
 * 			• org.springframework.context.event.ContextStartedEvent		Spring 应用上下文停止事件
 * 			• org.springframework.context.event.ContextStoppedEvent     Spring 应用上下文关闭事件
 *
 *
 * 	基于接口的 Spring 事件监听器
 * 		• Java 标准事件监听器 java.util.EventListener 扩展
 * 		• 扩展接口 - org.springframework.context.ApplicationListener
 * 		• 设计特点：单一类型事件处理
 * 		• 处理方法：onApplicationEvent(ApplicationEvent)
 * 		• 事件类型：org.springframework.context.ApplicationEvent
 *
 * 基于注解的 Spring 事件监听器
 * 	• Spring 注解 - @org.springframework.context.event.EventListener
 * 		* 设计特点：支持多 ApplicationEvent 类型，无需接口约束
 * 		* 注解目标：方法
 * 		* 是否支持异步执行：支持
 * 		* 是否支持泛型类型事件：支持
 * 		* 是否支持顺序控制：支持，配置 @Order 注解控制
 *
 * 注册 Spring ApplicationListener
 * 	• 方法一：ApplicationListener 作为 Spring Bean 注册
 * 	• 方法二：通过 ConfigurableApplicationContext API 注册
 *
 * Spring 事件发布器
 * • 方法一：通过 ApplicationEventPublisher 发布 Spring 事件，也叫做事件发布器，它与 java.util.Observable 类似，是事件发布者
 * 		• 获取 ApplicationEventPublisher
 * 			• 依赖注入，去实现 ApplicationEventPublisherAware 接口（Spring 生命周期的各种 Aware接口）
 * • 方法二：通过 ApplicationEventMulticaster 发布 Spring 事件，也叫做事件广播
 * 		• 获取 ApplicationEventMulticaster
 * 			• 依赖注入
 * 			• 依赖查找
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-18 17:25:01
 */
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 将引导类作为 Configuration Class
		applicationContext.register(ApplicationListenerDemo.class);

		// 监听方式一：基于接口的 Spring 事件监听器
		// 注册方式一： Spring ApplicationListener, 通过 ConfigurableApplicationContext API 注册
		applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				print("ApplicationListener - 接收到 Spring 事件：" + event);
			}
		});

		// 注册方式二：ApplicationListener 作为 Spring Bean 注册，
		// 把 ApplicationListener 注册为一个 Spring Bean，去实现 ApplicationListener 接口，然后通过 Configuration Class 来注册
		applicationContext.register(MyApplicationListener.class); // 通过 Configuration Class 来注册


		// 监听方式二：基于注解的 Spring 事件监听器 @EventListener

		// 启动 Spring 应用上下文
		applicationContext.refresh();
		// 启动 Spring 上下文
		applicationContext.start();
		// 停止 Spring 上下文
		applicationContext.stop();
		// 关闭 Spring 应用下下文
		applicationContext.close();
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		applicationEventPublisher.publishEvent(new ApplicationEvent("Anonymous Hello, Word") {
		}); // 发布一个事件，使用匿名类

		applicationEventPublisher.publishEvent("Any Hello, World"); // 发布一个事件（PayloadApplicationEvent）
	}

	static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

		@Override
		public void onApplicationEvent(ContextRefreshedEvent event) {
			print("MyApplicationListener(onApplicationEvent) -- 接收到 Spring 事件： ContextRefreshedEvent");
		}
	}

	// 处理多个事件
	@EventListener
	public void onApplicationEvent(ApplicationEvent event) {
		print("@EventListener(onApplicationEvent) -- 接收到 Spring 事件：" + event.getClass().getSimpleName());
	}

	@EventListener
	public void onApplicationContextRefreshedEvent(ContextRefreshedEvent event) {
		print("@EventListener(onApplicationContextRefreshedEvent) -- 接收到 Spring 事件： ContextRefreshedEvent");
	}

	@EventListener
	@Async
	public void onApplicationContextRefreshedEventAsync(ContextRefreshedEvent event) {
		print("@EventListener(onApplicationContextRefreshedEventAsync) -- 接到到 Spring 事件： ContextRefreshedEvent");
	}

	@EventListener
	@Order(2)
	public void onApplicationContextRefreshedEventOrder2(ContextRefreshedEvent event) {
		print("@EventListener(onApplicationContextRefreshedEventOrder2) -- 接到到 Spring 事件： ContextRefreshedEvent");
	}

	@EventListener
	@Order(1)
	public void onApplicationContextRefreshedEventOrder1(ContextRefreshedEvent event) {
		print("@EventListener(onApplicationContextRefreshedEventOrder1) -- 接到到 Spring 事件： ContextRefreshedEvent");
	}

	@EventListener
	public void onApplicationContextStaredEvent(ContextStartedEvent event) {
		print("@EventListener(onApplicationContextStaredEvent) -- 接收到 Spring 事件： ContextStartedEvent");
	}

	@EventListener
	public void onApplicationContextStoppedEvent(ContextStoppedEvent event) {
		print("@EventListener(onApplicationContextStoppedEvent) -- 接收到 Spring 事件： ContextStoppedEvent");
	}

	@EventListener
	public void onApplicationContextClosedEvent(ContextClosedEvent event) {
		print("@EventListener(onApplicationContextClosedEvent) -- 接收到 Spring 事件： ContextClosedEvent");
	}

	private static void print(Object printable) {
		System.out.printf("[线程：%s] : %s\n", Thread.currentThread().getName(), printable);
	}
}
