package com.zq.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 视频： 219丨依赖注入Environment.mp4
 * PTT：第十九章 Spring Environment 抽象（Environment Abstraction）.pdf
 *+
 * 依赖注入 Environment
 * • 直接依赖注入
 * 		• 通过 EnvironmentAware 接口回调（回调方式）
 * 		• 通过 @Autowired 注入 Environment（@Autowired 方式）
 * • 间接依赖注入
 * 		• 通过 ApplicationContextAware 接口回调（回调方式）
 * 		• 通过 @Autowired 注入 ApplicationContext（@Autowired 方式）
 *+
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-20 11:58:52
 */
public class InjectionEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

	private Environment environment; // 直接接口回调

	@Autowired
	private Environment environment2; // 直接 @Autowired

	private ApplicationContext applicationContext; // 间接回调

	@Autowired
	private ApplicationContext applicationContext2; // 间接 @Autowired

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		context.register(InjectionEnvironmentDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		// 获取到当前配置类 Bean
		InjectionEnvironmentDemo injectionEnvironmentDemo = context.getBean(InjectionEnvironmentDemo.class);
		System.out.println(injectionEnvironmentDemo.environment);

		// 注入的对象是不是相等的，是不是同一个对象，是不是一个单例对象，是不是和 context 里面的 environment 是同一个
		System.out.println(injectionEnvironmentDemo.environment == injectionEnvironmentDemo.environment2);
		System.out.println(injectionEnvironmentDemo.environment == context.getEnvironment());
		System.out.println(injectionEnvironmentDemo.environment == injectionEnvironmentDemo.applicationContext.getEnvironment());
		System.out.println(injectionEnvironmentDemo.environment == injectionEnvironmentDemo.applicationContext2.getEnvironment());

		System.out.println(injectionEnvironmentDemo.applicationContext == context);
		System.out.println(injectionEnvironmentDemo.applicationContext2 == context);

		// 关闭 Spring 应用上下文
		context.close();
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
