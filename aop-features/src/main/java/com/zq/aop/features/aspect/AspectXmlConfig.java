package com.zq.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 基于 XML 配置 Advice
 *
 * @author <a href="mailto:zhangquan@hupu.com">zhangquan</a>
 * @since  2023-10-23 17:43:15
 */
public class AspectXmlConfig {

	public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("@Around any public method: " + pjp.getSignature());
		return pjp.proceed();
	}

	public void beforeAnyPublicMethod() {
		System.out.println("@Before any public method.");
	}

}
