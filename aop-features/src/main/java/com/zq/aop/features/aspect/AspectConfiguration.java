package com.zq.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * Aspect 配置类，基于注解
 *
 * 在同一个Aspect里，一般 Around 在是 Before 之前执行
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-23 14:52:23
 */
@Aspect
@Order // 默认是最低优先级
public class AspectConfiguration {

	// 1. 声明 Pointcut，一般方法必须是 private，返回值是 void，方法体是空的，写了也不执行
	// 2. Pointcut 只是做筛选，它只是筛选出符合条件的 Joint Point，方法名则为 Joint Point
	// 3. Pointcut 和 Advice 是一对多的关系，一个 Pointcut 可以被多个 Advice 引用
	@Pointcut("execution(public * *(..))")  // 拦截或筛选出所有 public 方法
	private void anyPublicMethod() {

	}

	// Pointcut 筛选出 Joint Point，执行是 Advice
	@Before("anyPublicMethod()")
	public void beforeAnyPublicMethod() {
		System.out.println("@Before any public method.");
	}

	// @Around 注解和 @Before 注解的区别？
	// Before 不需要显示触发方法执行，Around 需要显式触发（pjp.proceed()）
	@Around("anyPublicMethod()")
	public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("@Around any public method.");
		return pjp.proceed();
	}
}
