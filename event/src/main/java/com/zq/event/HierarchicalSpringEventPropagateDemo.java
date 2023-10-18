package com.zq.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * Spring 层次性上下文事件传播
 * • 发生说明
 * 		当 Spring 应用出现多层次 Spring 应用上下文（ApplicationContext）时，如 Spring WebMVC、Spring Boot
 * 		或 Spring Cloud 场景下，由子 ApplicationContext 发起 Spring 事件可能会传递到其 Parent
 * 		ApplicationContext（直到 Root）的过程
 * • 如何避免
 * 		• 定位 Spring 事件源（ApplicationContext）进行过滤处理
 *
 * 视频：190丨Spring层次性上下文事件传播：这是一个Feature还是一个Bug？ 191丨Spring内建事件（Built-inEvents）：为什么ContextStartedEvent和ContextStoppedEvent是鸡肋事件？-深入剖析源码，掌握核心编程特性.mp4
 * PPT: 第十七章 Spring 事件（Events）.pdf
 *
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-18 19:02:30
 */
public class HierarchicalSpringEventPropagateDemo {
	public static void main(String[] args) {
		// 1. 创建 parent Spring 应用上下文
		AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
		parentContext.setId("parent-context");
		// 注册事件监听者到 parent Spring 应用上下文
		parentContext.register(MyApplicationListener.class);

		// 2. 创建 current Spring 应用上下文
		AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
		currentContext.setId("current-context");
		// 设置当前 Spring 应用上下文的父级上下文 current -> parent
		currentContext.setParent(parentContext);
		// 注册事件监听者到 current Spring 应用上下文
		currentContext.register(MyApplicationListener.class);

		parentContext.refresh();
		currentContext.refresh();

		currentContext.close();
		parentContext.close();
	}

	// 为什么 current-context 会有两次事件被监听到？
	// 第一个事件是由 parent-context 调用 refresh() 方法时被监听到了
	// 因为 current-context 会有两个应用上下文，一个是它自己，另一个是 parent ，这两次都被触发了
	static class MyApplicationListener implements ApplicationListener<ApplicationContextEvent> {

		private static Set<ApplicationContextEvent> processedEvents = new LinkedHashSet<>();
		//private Set<ApplicationContextEvent> processedEvents = new LinkedHashSet<>();

		@Override
		public void onApplicationEvent(ApplicationContextEvent event) {
			if (processedEvents.add(event)) {
				System.out.printf("监听到 Spring 应用上下文[ ID : %s ] 事件 :%s\n", event.getApplicationContext().getId(),
						event.getClass().getSimpleName());
			}
		}
	}
}
