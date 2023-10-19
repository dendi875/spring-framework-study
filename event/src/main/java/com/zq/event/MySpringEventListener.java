package com.zq.event;

import org.springframework.context.ApplicationListener;

/**
 *
 * 自定义 Spring 事件监听器
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 10:35:30
 */
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {

	@Override
	public void onApplicationEvent(MySpringEvent event) {
		System.out.printf("[线程：%s] 监听到事件：%s\n", Thread.currentThread().getName(), event);
	}
}
