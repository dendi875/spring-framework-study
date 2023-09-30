package com.zq.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 40丨回收Spring Bean：Spring IoC容器管理的Bean能够被垃圾回收吗？.mp4
 *
 * Bean 垃圾回收
 * 1. 关闭 Spring 容器
 * 2. 执行GC
 * 3. Spring Bean 中覆盖的 finalize() 方法被回调 {@link }
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-13 21:48:39
 */
public class BeanGarbageCollectionDemo {

	public static void main(String[] args) throws InterruptedException {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册配置类
		applicationContext.register(BeanInitializationAndDestroyDemo.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		BeanInitializationAndDestroyDemo.initAndDestroy(applicationContext);

		applicationContext.close(); // 关闭 Spring 应用上下文

		// 强制GC
		Thread.sleep(5000L);
		System.gc(); // 多运行几次
		Thread.sleep(5000L);
	}
}
