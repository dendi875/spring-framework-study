package com.zq.aop.features.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-27 12:01:49
 */
public class StaticExecutor implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;

	// 传统的一种实现方式，偏静态的方式，就是方法执行完之后，再发布一个事件
	public void execute() {
		System.out.println("Executing...");
		applicationEventPublisher.publishEvent(new ExecutedEvent(this));
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
}
