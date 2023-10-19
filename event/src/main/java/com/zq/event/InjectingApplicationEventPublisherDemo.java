package com.zq.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 视频：194丨依赖注入 ApplicationEventPublisher：事件推送还会引起Bug？.mp4
 * PPT: 第十七章 Spring 事件（Events）.pdf
 *
 * 依赖注入 ApplicationEventPublisher
 * 	• 通过 ApplicationEventPublisherAware 回调接口
 * 	• 通过 @Autowired ApplicationEventPublisher
 *
 * 四种方式注入：
 * 1. 通过 ApplicationEventPublisherAware 回调接口
 * 2. 通过 ApplicationContextAware 回调接口
 * 3. 通过 @Autowired 注入 ApplicationEventPublisher
 * 4. 通过 @Autowired 注入 ApplicationContext
 *
 * 如果以上四种方式同时都注入了：
 * * ApplicationEventPublisherAware 的执行一定在 ApplicationContextAware 之前，看 ApplicationContextAwareProcessor
 * * @Autowired 注入 ApplicationEventPublisher、@Autowired 注入 ApplicationContext 这两个顺序谁前谁后不一定
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 11:06:46
 */
public class InjectingApplicationEventPublisherDemo
		implements ApplicationEventPublisherAware, ApplicationContextAware, BeanPostProcessor {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		applicationEventPublisher.publishEvent(new MySpringEvent("The event from @Autowired ApplicationEventPublisher"));
		applicationContext.publishEvent(new MySpringEvent("The event from @Autowired ApplicationContext") );
	}

	public static void main(String[] args) {
		// 创建基于注解驱动的 Spring 应用上下文
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class
		context.register(InjectingApplicationEventPublisherDemo.class);

		// 添加 Spring 事件监听器
		context.addApplicationListener(new MySpringEventListener());

		// 启动 Spring 应用上下文
		context.refresh();

		// 关闭 Spring 应用上文
		context.close();
	}


	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		applicationEventPublisher.publishEvent(new MySpringEvent("The event from ApplicationEventPublisherAware"));
	}

	// ApplicationContext 它实现了 ApplicationEventPublisher 接口，所以它就是一个 ApplicationEventPublisher
	// 通过一种层次性的方式，或者是说通过一种继承的方式间接依赖注入
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		applicationContext.publishEvent(new MySpringEvent("The event from ApplicationContextAware"));
	}
}
