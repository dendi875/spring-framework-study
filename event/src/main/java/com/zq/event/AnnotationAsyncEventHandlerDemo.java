package com.zq.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 异步方式事件处理器示例---基于注解实现
 *
 * 视频：197丨同步和异步Spring事件广播：Spring对J.U.CExecutor接口的理解不够？.mp4
 * PPT：第十七章 Spring 事件（Events）.pdf
 *
 * 基于注解 - @org.springframework.context.event.EventListener
 * • 模式切换
 * 		• 默认模式：同步
 * 		• 异步模式：标注 @org.springframework.scheduling.annotation.Async
 * • 实现限制：无法直接实现同步/异步动态切换
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 15:36:11
 */
@EnableAsync // 激活 Spring 异步特性
public class AnnotationAsyncEventHandlerDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		applicationContext.register(AnnotationAsyncEventHandlerDemo.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 发布自定义 Spring 事件
		applicationContext.publishEvent(new MySpringEvent("Hello, AsyncSprintEvent-Annotation"));

		// 关闭 Spring 应用上下文
		applicationContext.close();
	}

	@Bean
	public Executor taskExecutor() {
		return Executors.newSingleThreadExecutor(
				new CustomizableThreadFactory("my-spring-event-thread-pool-annotation")
		);
	}

	@EventListener
	@Async
	public void onEventListener(MySpringEvent event) {
		System.out.printf("[线程：%s] onEventListener方法监听到事件：%s\n", Thread.currentThread().getName(), event);
	}
}
