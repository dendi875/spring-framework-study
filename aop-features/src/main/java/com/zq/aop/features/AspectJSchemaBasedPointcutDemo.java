package com.zq.aop.features;

import com.zq.aop.overview.proxy.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于 XML 方式使用 pointcut 示例
 *
 * 视频：28丨XML配置Pointcut.mp4
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-23 17:38:17
 */
public class AspectJSchemaBasedPointcutDemo {

	public static void main(String[] args) {
		// 创建并启动 Spring 应用上下文
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");

		EchoService echoService = context.getBean("echoService", EchoService.class);

		System.out.println(echoService.echo("Hello, World"));

		// 关闭 Spring 应用上下文
		context.close();
	}
}
