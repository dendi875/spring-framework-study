package com.zq.aop.overview.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理实现
 *
 * 视频：08丨Java AOP代理模式（Proxy）：Java静态代理和动态代理的区别是什么？.mp4
 * PPT:  第一章 - Spring AOP 总览.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-22 16:43:49
 */
public class JdkDynamicProxyDemo {

	public static void main(String[] args) {

		// 第一个参数要传一个 ClassLoader，为什么要传 ClassLoader 因为它需要类加载方式
		// 动态代理它会生成新的字节码的类，它需要把新的类加载到 ClassLoader 里去
		// 第一个参数最好使用当前的 ClassLoader，还有一个 ClassLoader 是 EchoService.class 的 ClassLoader

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// 判断方法所在的类是 EchoService 或者它的子类
				if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
					ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
					return echoService.echo((String) args[0]);
				}
				return null;
			}
		});

		// 把代理对象转成我们目标对象
		EchoService echoService = (EchoService) proxy;
		echoService.echo("Hello, World");
	}
}
