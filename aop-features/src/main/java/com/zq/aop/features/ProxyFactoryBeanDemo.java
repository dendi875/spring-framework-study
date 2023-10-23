package com.zq.aop.features;

import com.zq.aop.overview.proxy.EchoService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * ProxyFactoryBean 的使用示例，它是通过上下文的依赖关系，通过名称的方式依赖查找得到相应的 Bean，然后组合到 ProxyFactoryBean 中去
 *
 * 视频：25丨XML配置驱动 - 创建AOP代理.mp4
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-23 15:16:26
 */
@Aspect // 声明为切面
@Configuration // Configuration Class，会被 CGLIB 提升
public class ProxyFactoryBeanDemo {

	public static void main(String[] args) {
		// 创建并启动 Spring 上下文
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");

		// 依赖查找方式找 Bean
		EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);

		System.out.println(echoService.echo("Hello, World"));

		context.close();
	}
}
