package com.zq.aop.features.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义的事件，在 {@link Executor#execute} 方法执行完后，去执行该事件
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-27 12:04:44
 */
public class ExecutedEvent extends ApplicationEvent {

	/**
	 * Create a new {@code ApplicationEvent}.
	 *
	 * @param source the object on which the event initially occurred or with
	 *               which the event is associated (never {@code null})
	 */
	public ExecutedEvent(Object source) {
		super(source);
	}
}
