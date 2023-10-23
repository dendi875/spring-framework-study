package com.zq.aop.overview.interceptor;

import com.zq.aop.overview.proxy.DefaultEchoService;
import com.zq.aop.overview.proxy.EchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 前置模式是在执行代码前做一个前置判断或前置动作，后置模式同理。
 * <p>
 * Java AOP 拦截器模式（Interceptor）
 * • 拦截类型
 * 		• 前置拦截（Before）
 * 		• 后置拦截（After）
 * 		• 异常拦截（Exception）
 * <p>
 * 视频：10丨Java AOP拦截器模式（Interceptor）：拦截执行分别代表什么？.mp4
 * PPT: 第一章 - Spring AOP 总览.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since 2023-10-22 17:40:41
 */
public class AopInterceptorDemo {

	public static void main(String[] args) {
		// 前置模式 + 后置模式
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
					// 前置拦截器
					BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
						@Override
						public Object before(Object proxy, Method method, Object[] args) {
							return System.currentTimeMillis();
						}
					};

					Long startTime = 0L;
					Long endTime = 0L;
					Object result = null;
					try {
						// 前置拦截
						startTime = (Long) beforeInterceptor.before(proxy, method, args);
						EchoService echoService = new DefaultEchoService();
						result = echoService.echo((String) args[0]); // 目标对象执行

						// 方法执行后置拦截器，相当于 AfterReturning
						AfterReturnInterceptor afterReturnInterceptor = new AfterReturnInterceptor() {
							@Override
							public Object after(Object proxy, Method method, Object[] args, Object returnResult) {
								return System.currentTimeMillis();
							}
						};
						// 后置拦截
						endTime = (Long) afterReturnInterceptor.after(proxy, method, args, result);
					} catch (Exception e) {
						// 异常拦截器（处理方法执行后），相当于 AfterThrowing
						ExceptionInterceptor interceptor = (proxy1, method1, args1, throwable) -> {
						};
					} finally {
						// finally 后置拦截器
						FinallyInterceptor interceptor = new TimeFinallyInterceptor(startTime, endTime); // 相当于 After
						Long costTime = (Long) interceptor.finalize(proxy, method, args, result);
						System.out.println("echo 方法执行的实现：" + costTime + " ms.");
					}

				}
				return null;
			}
		});

		EchoService echoService = (EchoService) proxy;
		echoService.echo("Hello,World");
	}
}


class TimeFinallyInterceptor implements FinallyInterceptor {

	private final Long startTime;

	private final Long endTime;

	TimeFinallyInterceptor(Long startTime, Long endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public Object finalize(Object proxy, Method method, Object[] args, Object returnResult) {
		// 方法执行时间（毫秒）
		Long costTime = endTime - startTime;
		return costTime;
	}
}
