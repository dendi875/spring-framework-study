package com.zq.aop.overview.predicate;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 *
 * 视频：08丨Java AOP代理模式（Proxy）：Java静态代理和动态代理的区别是什么？.mp4
 * PPT:  第一章 - Spring AOP 总览.pdf
 *
 * 通过Java反射API来判断和过滤目标对象的方法或类
 *
 * 主要就是 {@link Method} 的运用
 *
 * Java AOP 判断模式（Predicate）
 * • 判断来源
 * 		• 类型（Class）
 * 		• 方法（Method）
 * 		• 注解（Annotation）
 * 		• 参数（Parameter）
 * 		• 异常（Exception）
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-22 17:11:06
 */
public class TargetFilterDemo {

	public static void main(String[] args) throws ClassNotFoundException {

		String targetClassName = "com.zq.aop.overview.proxy.EchoService";

		// 获取目标类，需要一个 ClassLoader，所以获取当前线程的 ClassLoader
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		// 获取目标类
		Class<?> targetClass = classLoader.loadClass(targetClassName);

		// 借助 Spring 反射工具类来过滤出我们需要的方法 String echo(String message);
		Method targetMethod = ReflectionUtils.findMethod(targetClass, "echo", String.class);
		System.out.println(targetMethod);

		// 过滤出 targetClass 类中，方法名为 echo 参数为 String，方法异常签名为 NullPointerException 的方法
		ReflectionUtils.doWithMethods(targetClass,
				new ReflectionUtils.MethodCallback() {

					@Override
					public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
						System.out.println("仅抛出 NullPointerException 方法为：" + method);
					}
				},
				new ReflectionUtils.MethodFilter() {

					@Override
					public boolean matches(Method method) {
						Class<?>[] parameterTypes = method.getParameterTypes();
						Class<?>[] exceptionTypes = method.getExceptionTypes();
						return parameterTypes.length == 1 &&
								String.class.equals(parameterTypes[0]) &&
								exceptionTypes.length == 1 &&
								NullPointerException.class.equals(exceptionTypes[0]);
					}
				});
	}
}
