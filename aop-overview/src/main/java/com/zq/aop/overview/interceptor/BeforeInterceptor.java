package com.zq.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 * 前置拦截器
 *
 * 把 AopInterceptorDemo.invoke 里的前置逻辑抽象出一个前置拦截器
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-22 17:55:33
 */
public interface BeforeInterceptor {

	/**
	 * 前置执行
	 * @param proxy 目标对象的代理对象
	 * @param method
	 * @param args
	 * @return
	 */
	Object before(Object proxy, Method method, Object[] args);
}
