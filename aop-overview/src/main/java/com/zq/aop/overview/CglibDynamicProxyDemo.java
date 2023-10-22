package com.zq.aop.overview;

import com.zq.aop.overview.proxy.DefaultEchoService;
import com.zq.aop.overview.proxy.EchoService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Spring 基于 CGLIB 实现 AOP 的示例
 * <p>
 * 视频：16丨JDK动态代理：为什么Proxy.newProxyInstance会生成新的字节码？17丨CGLIB动态代理：为什么Java动态代理无法满足AOP的需要？.mp4
 * PPT: 第一章 - Spring AOP 总览.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @see org.springframework.cglib.proxy.Enhancer
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor
 * @see org.springframework.context.annotation.ConfigurationClassEnhancer
 * @since 2023-10-22 20:53:42
 */
public class CglibDynamicProxyDemo {

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();

		// 1. 指定 super class
		Class<?> superClass = DefaultEchoService.class;
		enhancer.setSuperclass(superClass);

		// 2. 指定拦截器接口
		enhancer.setInterfaces(new Class[]{EchoService.class});
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object source, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				long startTime = System.currentTimeMillis();
				// source 是被 CGLIB 提升后的一个子类
				// 我们需要调用的是目标类： DefaultEchoService
				// 错误使用
//                Object result = method.invoke(source, args);
				// 正确的方法调用
				Object result = methodProxy.invokeSuper(source, args);
				long costTime = System.currentTimeMillis() - startTime;

				System.out.println("[CGLIB 字节码提升] echo 方法执行的实现：" + costTime + " ms.");
				return result;
			}
		});

		// 3. 创建代理对象
		EchoService echoService = (EchoService) enhancer.create();

		System.out.println(echoService.echo("Hello, World"));

	}
}
