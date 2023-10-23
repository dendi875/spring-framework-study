package com.zq.aop.features.pointcut;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * API 的方式实现 Pointcut，相比注解和xml方式更加灵活，可以添加我自己的匹配逻辑
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-23 18:13:21
 */
public class EchoServicePointcut extends StaticMethodMatcherPointcut {

	private String methodName;

	private Class<?> targetClass;

	public EchoServicePointcut(String methodName, Class<?> targetClass) {
		this.methodName = methodName;
		this.targetClass = targetClass;
	}

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return Objects.equals(methodName, method.getName()) &&
				this.targetClass.isAssignableFrom(targetClass); // 参数 targetClass 是 this.targetClass 的类或子类
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
	}
}
