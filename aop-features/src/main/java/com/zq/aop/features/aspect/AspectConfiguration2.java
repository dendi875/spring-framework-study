package com.zq.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Aspect 配置类，基于注解
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-23 14:52:23
 */
@Aspect
public class AspectConfiguration2 implements Ordered {

	// 1. 声明 Pointcut，一般方法必须是 private，返回值是 void，方法体是空的，写了也不执行
	// 2. Pointcut 只是做筛选，它只是筛选出符合条件的 Joint Point，方法名则为 Joint Point
	// 3. Pointcut 和 Advice 是一对多的关系，一个 Pointcut 可以被多个 Advice 引用
	//@Pointcut("execution(public * *(..))")  // 拦截或筛选出所有 public 方法
	//private void anyPublicMethod() {
	//
	//}

	// Pointcut 筛选出 Joint Point，执行是 Advice
	@Before("execution(public * *(..))") // 可以直接写 Pointcut
	public void beforeAnyPublicMethod() {
		System.out.println("@Before any public method(2).");
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
