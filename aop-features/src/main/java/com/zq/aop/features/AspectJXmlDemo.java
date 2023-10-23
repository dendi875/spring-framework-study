package com.zq.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 激活 @AspectJ 模块：XML 配置激活
 * 	<aop:aspectj-autoproxy/>
 *
 *
 * 视频：23丨@AspectJ注解驱动.mp4
 * PPT: 第二章：Spring AOP 基础.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-23 14:19:21
 */
@Aspect // 声明为切面
@Configuration // Configuration Class，会被 CGLIB 动态提升，这个注解不能少
public class AspectJXmlDemo {

	public static void main(String[] args) {
		// 创建并启动 Spring 应用上下文
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");

		// 依赖查找获取 Bean
		//AspectJXmlDemo aspectJXmlDemo = context.getBean(AspectJXmlDemo.class);

		// 关闭 Spring 应用上下文
		context.close();
	}
}
