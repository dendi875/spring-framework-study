package com.zq.aop.features;

import com.zq.aop.features.aspect.AspectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 视频：27丨@AspectJ Pointcut指令与表达式：为什么Spring只能有限支持？.mp4
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-23 16:37:16
 */
@EnableAspectJAutoProxy // 活动 AspectJ 注解驱动的自动代理能力
public class AspectJAnnotatedPointcutDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		context.register(AspectJAnnotatedPointcutDemo.class, AspectConfiguration.class);

		// 启动 Spring 应用上下文
		context.refresh();

		// 依赖查找方式获取 Bean
		AspectJAnnotatedPointcutDemo aspectJAnnotatedPointcutDemo = context.getBean(AspectJAnnotatedPointcutDemo.class);

		aspectJAnnotatedPointcutDemo.execute();

		// 关闭 Spring 应用上下文
		context.close();
	}

	public void execute() {
		System.out.println("execute()...");
	}

}
