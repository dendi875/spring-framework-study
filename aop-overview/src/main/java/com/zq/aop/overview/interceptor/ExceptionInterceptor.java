package com.zq.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 * 异常拦截器
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since 2023-10-22 17:58:55
 */
public interface ExceptionInterceptor {

	/**
	 *
	 * @param proxy
	 * @param method
	 * @param args
	 * @param throwable
	 */
	void intercept(Object proxy, Method method, Object[] args, Throwable throwable);

}