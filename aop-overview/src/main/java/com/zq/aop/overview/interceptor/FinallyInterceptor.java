package com.zq.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 *
 * 最终执行的拦截器
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-22 17:58:55
 */	
public interface FinallyInterceptor {

	/**
	 *
	 * @param proxy
	 * @param method
	 * @param args
	 * @param resultResult 方法执行的返回结果
	 * @return
	 */
	Object finalize(Object proxy, Method method, Object[] args, Object resultResult);

}