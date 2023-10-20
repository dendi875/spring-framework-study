package com.zq.application.context.lifecycle;

import org.springframework.context.Lifecycle;

/**
 * {@link Lifecycle} 的实现
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since 2023-10-20 21:05:53
 */
public class MyLifecycle implements Lifecycle {

	private boolean running;

	@Override
	public void start() {
		running = true;
		System.out.println("MyLifecycle 正在启动...");
	}

	@Override
	public void stop() {
		System.out.println("MyLifecycle 正在停止...");
		running = false;
	}

	@Override
	public boolean isRunning() {
		return running;
	}
}
