package com.zq.aop.overview.interceptor;

import java.lang.reflect.Method;

/**
 *
 * 把 AopInterceptorDemo.invoke 里的后置逻辑抽象出一个后置拦截器
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-22 17:58:55
 */	
public interface AfterReturnInterceptor {

	/**
	 *
	 * @param proxy
	 * @param method
	 * @param args
	 * @param resultResult 方法执行的返回结果
	 * @return
	 */
	Object after(Object proxy, Method method, Object[] args, Object resultResult);

}