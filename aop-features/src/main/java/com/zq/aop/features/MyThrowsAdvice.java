package com.zq.aop.features;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @see org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-24 16:20:15
 */
public class MyThrowsAdvice implements ThrowsAdvice {

	//public void afterThrowing(RuntimeException ex) {
	//	System.out.printf("Exception: %s\n", ex);
	//}

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
		System.out.printf("Method: %s, args: %s, target: %s, exception: %s\n",
				method,
				Arrays.asList(args),
				target,
				ex
		);
	}
}
