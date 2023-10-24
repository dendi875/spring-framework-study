package com.zq.aop.features;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zq.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.zq.aop.features.pointcut.EchoServiceEchoMethodPointcut;
import com.zq.aop.features.pointcut.EchoServicePointcut;
import com.zq.aop.overview.proxy.DefaultEchoService;
import com.zq.aop.overview.proxy.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 基于 API 的方式来使用 Pointcut
 * <p>
 * 核心API: {@link org.springframework.aop.Pointcut}
 * 适配实现：{@link org.springframework.aop.support.DefaultPointcutAdvisor}
 * <p>
 * 视频：29丨API实现Pointcut.mp4
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since 2023-10-23 18:08:46
 */
public class PointcutAPIDemo {

	public static void main(String[] args) {
		// 创建 Pointcut
		EchoServicePointcut extendsPointcut = new EchoServicePointcut("echo", EchoService.class);
		EchoServiceEchoMethodPointcut interfacePointcut = EchoServiceEchoMethodPointcut.INSTANCE;

		ComposablePointcut pointcut = new ComposablePointcut(interfacePointcut);
		// 组合实现，交集
		pointcut.intersection(extendsPointcut.getClassFilter());
		pointcut.intersection(extendsPointcut.getMethodMatcher());

		// 添加 advisor，这里需要把 Pointcut 转换为 advisor，通过 DefaultPointcutAdvisor 来转换
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceMethodInterceptor());

		// 使用 ProxyFactory
		// 创建目标对象并注入目标对象
		DefaultEchoService defaultEchoService = new DefaultEchoService();
		ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);

		// 添加 advisor
		proxyFactory.addAdvisor(advisor);

		// 获取代理对象
		EchoService echoService = (EchoService) proxyFactory.getProxy();
		System.out.println(echoService.echo("Hello, World"));
	}
}
