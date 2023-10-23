package com.zq.aop.features;

import com.zq.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.zq.aop.overview.proxy.DefaultEchoService;
import com.zq.aop.overview.proxy.EchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 *
 * ProxyFactory 使用实例
 *
 * 1. ProxyFactory 它是脱离了 Spring IoC 容器，它并没用应用到 Spring 上下文
 * 2. 通过 DEBUG 可以知道 ProxyFactory 默认情况下是基于 JDK 动态代理创建代理对象
 * 3. ProxyFactory 它是一个更底层的API
 *
 * 视频：26丨标准代理工厂API - ProxyFactory.mp4
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-23 16:00:12
 */
public class ProxyFactoryDemo {

	public static void main(String[] args) {
		// 首先要确认我们的目标对象（被代理的对象）
		DefaultEchoService defaultEchoService = new DefaultEchoService();
		// 注入目标对象
		ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);

		// 添加 Advice 实现
		// EchoServiceMethodInterceptor 继承 MethodInterceptor，
		// MethodInterceptor 继承 Interceptor
		// Interceptor 继承 Advice
		proxyFactory.addAdvice(new EchoServiceMethodInterceptor());

		// 获取代理对象
		EchoService echoService = (EchoService) proxyFactory.getProxy();
		System.out.println(echoService.echo("Hello, World"));
	}
}
