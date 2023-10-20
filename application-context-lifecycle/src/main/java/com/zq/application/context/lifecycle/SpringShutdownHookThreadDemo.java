package com.zq.application.context.lifecycle;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/**
 * 视频：243丨Spring应用上下文关闭阶段.mp4
 * PPT: 第二十章 Spring 应用上下文生命周期（ApplicationContext Lifecycle）.pdf
 *
 * Spring Shutdown Hook 线程示例
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-20 21:27:26
 */
public class SpringShutdownHookThreadDemo {

	public static void main(String[] args) throws IOException {

		GenericApplicationContext context = new GenericApplicationContext();

		// 添加事件监听器，来证明是另外的线程执行了 doClose()，kill -15 <pid>
		context.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
			@Override
			public void onApplicationEvent(ContextClosedEvent event) {
				System.out.printf("[线程：%s] ContextClosedEvent 处理\n", Thread.currentThread().getName());
			}
		});

		// 刷新 Spring 应用上下文
		context.refresh();

		// 注册 Shutdown Hook
		context.registerShutdownHook();

		// 模拟阻塞情况
		System.out.println("按任意键继续并且关闭 Spring 应用上下文");
		System.in.read();

		// 关闭 Spring 应用上下文
		context.close(); // 同步执行 doClose()
	}
}
