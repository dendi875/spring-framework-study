package com.zq.application.context.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 视频：242丨Spring应用上下文停止阶段.mp4
 * 		241丨Spring应用上下文启动阶段.mp4
 * PPT: 第二十章 Spring 应用上下文生命周期（ApplicationContext Lifecycle）.pdf
 *
 * Spring 应用上下文停止阶段
 * • AbstractApplicationContext#stop() 方法
 * 		• 停止 LifecycleProcessor
 * 			• 依赖查找 Lifecycle Beans
 * 			• 停止 Lifecycle Beans
 * 		• 发布 Spring 应用上下文已停止事件 - ContextStoppedEvent
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-20 21:07:53
 */
public class LifecycleDemo {

	public static void main(String[] args) {
		GenericApplicationContext context = new GenericApplicationContext();

		// 将 MyLifecycle 注册为一个 Spring Bean
		context.registerBeanDefinition("myLifecycle",
				BeanDefinitionBuilder.rootBeanDefinition(MyLifecycle.class).getBeanDefinition()
		);

		// 刷新 Spring 应用上下文
		context.refresh();

		// 启动 Spring 应用上下文
		context.start();

		// 停止 Spring 应用上下文
		context.stop();

		// 关闭 Spring 应用上下文
		context.close();
	}
}
