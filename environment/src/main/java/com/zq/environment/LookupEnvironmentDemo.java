package com.zq.environment;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import static org.springframework.context.ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME;

/**
 * 视频：220丨依赖查找Environment.mp4
 * PPT: 第十九章 Spring Environment 抽象（Environment Abstraction）.pdf
 *
 * 依赖查找 Environment
 * • 直接依赖查找
 * 		• 通过 org.springframework.context.ConfigurableApplicationContext#ENVIRONMENT_BEAN_NAME
 * • 间接依赖查找
 * 		• 通过 org.springframework.context.ConfigurableApplicationContext#getEnvironment
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-20 13:42:19
 */
public class LookupEnvironmentDemo implements EnvironmentAware {

	private Environment environment;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		context.register(LookupEnvironmentDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		// 依赖查找方式获取 Bean
		LookupEnvironmentDemo lookupEnvironmentDemo = context.getBean(LookupEnvironmentDemo.class);

		// 依赖查找方式获取 Bean
		Environment environment = context.getBean(ENVIRONMENT_BEAN_NAME, Environment.class);

		// 验证通过依赖查找方式获取的 Environment 对象和 依赖注入的 Environment 对象是否是同一个
		System.out.println(lookupEnvironmentDemo.environment == environment);

		// 关闭 Spring 应用上下文
		context.close();
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
