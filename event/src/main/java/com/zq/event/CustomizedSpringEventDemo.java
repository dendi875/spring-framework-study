package com.zq.event;

import org.springframework.context.support.GenericApplicationContext;

/**
 * 视频：193丨自定义Spring事件：自定义事件业务用得上吗？.mp4
 * PPT: 第十七章 Spring 事件（Events）.pdf
 *
 * 自定义 Spring 事件
 * • 扩展 org.springframework.context.ApplicationEvent
 * • 实现 org.springframework.context.ApplicationListener
 * • 注册 org.springframework.context.ApplicationListener
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 10:31:46
 */
public class CustomizedSpringEventDemo {
	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext();

		// 1. 注册自定义Spring事件监听器
		applicationContext.addApplicationListener(new MySpringEventListener());

		// 2. 启动 Spring 上下文
		applicationContext.refresh();

		// 3. 发布自定义事件
		applicationContext.publishEvent(new MySpringEvent("Hello, World"));

		// 4. 关闭 Spring 应用上下文
		applicationContext.close();
	}
}
