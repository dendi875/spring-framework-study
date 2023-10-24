package com.zq.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Random;

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
		Random random = new Random();
		if (random.nextBoolean()) {
			throw new RuntimeException("故意抛出异常");
		}

		System.out.println("@Before any public method.");
	}

	// @Around 注解和 @Before 注解的区别？
	// Before 不需要显示触发方法执行，Around 需要显式触发（pjp.proceed()）
	@Around("anyPublicMethod()")
	public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("@Around any public method.");
		return pjp.proceed();
	}

	/**********************************************************************************
	 * AfterReturning 最后执行（方法结束时执行），它不是一定需要方法有返回值，方法可以没有返回值
	 * After 相当于 try..finally 必须执行，不是最后执行的意思，是必须执行的意思
	 * AfterThrowing 在抛出异常时执行，这个异常也包含在比如 Before 时发生的异常，它相当于 catch 这种操作
	 *
	 **********************************************************************************/
	@AfterReturning("anyPublicMethod()")
	public void afterReturningAnyPublicMethod() {
		System.out.println("@AfterReturning any public method.");
	}

	@After("anyPublicMethod()")
	public void afterAnyPublicMethod() {
		System.out.println("@After any public method.");
	}

	@AfterThrowing("anyPublicMethod()")
	public void	afterThrowingAnyPublicMethod() {
		System.out.println("@AfterThrowing any public method.");
	}
}
