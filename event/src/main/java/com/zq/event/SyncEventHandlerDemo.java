package com.zq.event;

import org.springframework.context.support.GenericApplicationContext;

/**
 * 同步方式事件处理器示例
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 14:59:22
 */
public class SyncEventHandlerDemo {

	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext();

		// 添加 Spring 事件监听器
		applicationContext.addApplicationListener(new MySpringEventListener());

		// 启动 Spring 上下文
		applicationContext.refresh();

		// 发布自定义 Spring 事件
		applicationContext.publishEvent(new MySpringEvent("Hell, SpringEvent"));

		// 关闭 Spring 上下文
		applicationContext.close();
	}
}
