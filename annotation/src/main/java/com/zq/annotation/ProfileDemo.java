package com.zq.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * {@link Profile} 示例
 * <p>
 * 视频：211丨Spring条件注解.mp4
 * PPT: 第十八章 Spring 注解（Annotations）.pdf
 *
 * Spring 条件注解
 * • 基于配置条件注解 - @org.springframework.context.annotation.Profile
 * 		• 关联对象 - org.springframework.core.env.Environment 中的 Profiles
 * 		• 实现变化：从 Spring 4.0 开始，@Profile 基于 @Conditional 实现
 * • 基于编程条件注解 - @org.springframework.context.annotation.Conditional
 * 		• 关联对象 - org.springframework.context.annotation.Condition 具体实现
 *
 * Spring 条件注解
 * • @Conditional 实现原理
 * • 上下文对象 - org.springframework.context.annotation.ConditionContext
 * • 条件判断 - org.springframework.context.annotation.ConditionEvaluator 条件评估器
 * • 配置阶段 -
 * 		org.springframework.context.annotation.ConfigurationCondition.ConfigurationPhase
 * 	• 判断入口 - org.springframework.context.annotation.ConfigurationClassPostProcessor
 * 		• org.springframework.context.annotation.ConfigurationClassParser
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @see Profile
 * @see Environment#getActiveProfiles()
 * @since 2023-10-20 09:48:53
 */
public class ProfileDemo {

	// Bean 名称是可以重复的，条件注解是互斥的
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		applicationContext.register(ProfileDemo.class);

		// 获取 Environment 对象（可配置的或者可写入的）
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		// 我们可以设置一个默认或者兜底的 profiles
		environment.setDefaultProfiles("odd");

		// 还可以设置一个活跃的 profiles
		// environment.setActiveProfiles("even");
		// 活跃的 profile 可以通过启动参数来设置
		// --spring.profiles.active=even 这是在 Spring Boot 里在IDEA Program arguments: 中添加
		// -Dspring.profiles.active=even 这是在 Spring Framework 里在 IDEA 中的 VM options: 中添加


		// 启动 Spring 上下文
		applicationContext.refresh();

		// 依赖查找
		Integer number = applicationContext.getBean("number", Integer.class);
		System.out.println(number);

		// 关闭 Spring 上下文
		applicationContext.close();
	}

	@Bean(name = "number") // 返回奇数的 Bean
	@Profile("odd") // 奇数--配置条件注解
	public Integer odd() {
		return 1;
	}

	@Bean(name = "number") // 返回偶数的 Bean
	//@Profile("even") // 偶数--配置条件注解
	@Conditional(EvenProfileCondition.class) // 偶数--基于编程条件来实现
	public Integer even() {
		return 2;
	}
}
