package com.zq.aop.overview.interceptor;

import com.zq.aop.overview.proxy.DefaultEchoService;
import com.zq.aop.overview.proxy.EchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * 前置模式是在执行代码前做一个前置判断或前置动作，后置模式同理。
 *
 * Java AOP 拦截器模式（Interceptor）
 * • 拦截类型
 * 		• 前置拦截（Before）
 * 		• 后置拦截（After）
 * 		• 异常拦截（Exception）
 *
 * 视频：10丨Java AOP拦截器模式（Interceptor）：拦截执行分别代表什么？.mp4
 * PPT: 第一章 - Spring AOP 总览.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-22 17:40:41
 */
public class AopInterceptorOriginDemo {

	public static void main(String[] args) {
		// 基于 JDK 动态代理
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// 判断方法所在的类是 EchoService 或者它的子类
				if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
					long st = System.currentTimeMillis();
					try {
						EchoService echoService = new DefaultEchoService();
						return echoService.echo((String) args[0]);
					} finally {
						long duration = System.currentTimeMillis() - st;
						System.out.println("方法执行耗时：" + duration + "ms");
					}
				}
				return null;
			}
		});

		// 把代理对象转成我们目标对象
		EchoService echoService = (EchoService) proxy;
		echoService.echo("Hello, World");
	}
}
