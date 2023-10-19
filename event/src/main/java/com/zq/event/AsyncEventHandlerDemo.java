package com.zq.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步方式事件处理器示例---基于实现类
 *
 * 视频：197丨同步和异步Spring事件广播：Spring对J.U.CExecutor接口的理解不够？.mp4
 * PPT: 第十七章 Spring 事件（Events）.pdf
 *
 *  同步和异步 Spring 事件广播
 *
 * • 基于实现类 - org.springframework.context.event.SimpleApplicationEventMulticaster
 * 		• 模式切换：setTaskExecutor(java.util.concurrent.Executor) 方法
 * 			• 默认模式：同步
 * 			• 异步模式：如 java.util.concurrent.ThreadPoolExecutor
 * • 设计缺陷：非基于接口契约编程
 *
 *
 * 视频：198丨Spring4.1事件异常处理：ErrorHandler使用有怎样的限制？.mp4
 * PPT: 第十七章 Spring 事件（Events）.pdf
 *
 * Spring 4.1 事件异常处理
 * • Spring 3.0 错误处理接口 - org.springframework.util.ErrorHandler
 * • 使用场景
 * 		• Spring 事件（Events）
 * 			• SimpleApplicationEventMulticaster Spring 4.1 开始支持
 * 		• Spring 本地调度（Scheduling）
 * 			• org.springframework.scheduling.concurrent.ConcurrentTaskScheduler
 * 			• org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 15:02:24
 */
public class AsyncEventHandlerDemo {

	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext();

		// 添加 Spring 事件监听器
		applicationContext.addApplicationListener(new MySpringEventListener());

		// 启动 Spring 上下文
		applicationContext.refresh(); // 会初始化 ApplicationEventMulticaster

		// 依赖查找 ApplicationEventMulticaster
		ApplicationEventMulticaster applicationEventMulticaster
				= applicationContext.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
		// 判断当前 ApplicationEventMulticaster 是否为 SimpleApplicationEventMulticaster
		if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
			SimpleApplicationEventMulticaster simpleApplicationEventMulticaster =
					(SimpleApplicationEventMulticaster) applicationEventMulticaster; // 需强转

			// 切换线程池，目的是为了能够自定义线程池名称
			ExecutorService taskExecutor = Executors.newSingleThreadExecutor(
					new CustomizableThreadFactory("my-spring-event-thread-pool")
			);

			// 同步转异步
			simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);

			// 监听 ContextClosedEvent 事件，这样就能够优雅关闭线程池
			applicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
				@Override
				public void onApplicationEvent(ContextClosedEvent event) {
					if (!taskExecutor.isShutdown()) {
						taskExecutor.shutdown();
					}
				}
			});

			// 添加事件监听器处理事件过程中的异常处理器
			simpleApplicationEventMulticaster.setErrorHandler((t) -> {
				System.err.println("Spring 监听器在处理事件时发生了异常，原因：" + t.getMessage());
			});
		}

		// 添加事件监听器，模拟处理事件时发生了异常
		applicationContext.addApplicationListener(new ApplicationListener<MySpringEvent>() {

			@Override
			public void onApplicationEvent(MySpringEvent event) {
				throw new RuntimeException("故意抛出异常");
			}
		});

		// 发布 Spring 自定义事件
		applicationContext.publishEvent(new MySpringEvent("Hello, AsyncSpringEvent"));

		// 关闭 Spring 应该上下文，这时会发送 ContextClosedEvent 事件
		applicationContext.close();
	}
}
