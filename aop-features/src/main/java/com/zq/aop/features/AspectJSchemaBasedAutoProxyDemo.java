package com.zq.aop.features;

import com.zq.aop.overview.proxy.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 视频：39丨自动动态代理.mp4
 *
 * 自动动态代理
 * • 代表实现
 * 		• org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator
 * 		• org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
 * 		• org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-24 11:21:45
 */
public class AspectJSchemaBasedAutoProxyDemo {

	public static void main(String[] args) {
		// 创建并启动 Spring 应用上下文
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-auto-proxy-context.xml");

		EchoService echoService = context.getBean("echoService", EchoService.class);

		System.out.println(echoService.echo("Hello, World"));

		// 关闭 Spring 应用上下文
		context.close();
	}
}
