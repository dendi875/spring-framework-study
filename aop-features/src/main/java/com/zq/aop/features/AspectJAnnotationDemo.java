package com.zq.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * 激活 @AspectJ 模块：注解激活 @EnableAspectJAutoProxy
 *
 *
 * 视频：23丨@AspectJ注解驱动.mp4
 * PPT: 第二章：Spring AOP 基础.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-23 14:19:21
 */
@Aspect // 声明为切面
@EnableAspectJAutoProxy // 激活 Aspect 注解自动代理能力
@Configuration // Configuration Class，会被 CGLIB 动态提升，这个注解不能少
public class AspectJAnnotationDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		context.register(AspectJAnnotationDemo.class);

		// 刷新 Spring 应用上下文
		context.refresh();

		AspectJAnnotationDemo aspectJAnnotationDemo = context.getBean(AspectJAnnotationDemo.class);

		// 关闭 Spring 应用上下文
		context.close();
	}
}
