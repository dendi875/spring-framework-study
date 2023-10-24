package com.zq.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Random;

/**
 * 基于 XML 配置 Advice
 *
 * @author <a href="mailto:zhangquan@hupu.com">zhangquan</a>
 * @since  2023-10-23 17:43:15
 */
public class AspectXmlConfig {

	public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
		Random random = new Random();
		if (random.nextBoolean()) {
			throw new RuntimeException("AspectXmlConfig 故意抛出异常");
		}

		System.out.println("@Around any public method: " + pjp.getSignature());
		return pjp.proceed();
	}

	public void beforeAnyPublicMethod() {
		System.out.println("@Before any public method.");
	}

	public void afterReturningAnyPublicMethod() {
		System.out.println("@AfterReturning any public method.");
	}

	public void afterAnyPublicMethod() {
		System.out.println("@After any public method.");
	}

	public void	afterThrowingAnyPublicMethod() {
		System.out.println("@AfterThrowing any public method.");
	}
}
