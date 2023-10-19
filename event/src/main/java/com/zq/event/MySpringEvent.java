package com.zq.event;

import org.springframework.context.ApplicationEvent;

/**
 *
 * 自定义 Spring 事件
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 10:33:12
 */
public class MySpringEvent extends ApplicationEvent {

	/**
	 * Create a new {@code ApplicationEvent}.
	 *
	 * @param message the object on which the event initially occurred or with
	 *               which the event is associated (never {@code null})
	 */
	public MySpringEvent(String message) {
		super(message);
	}

	@Override
	public String getSource() {
		return (String) super.getSource();
	}
}
