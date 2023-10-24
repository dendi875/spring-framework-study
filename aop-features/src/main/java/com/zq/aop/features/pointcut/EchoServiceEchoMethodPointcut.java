package com.zq.aop.features.pointcut;

import com.zq.aop.overview.proxy.EchoService;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 视频：44丨Joinpoint条件接口 - Pointcut.mp4
 *
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-24 14:11:38
 */
public class EchoServiceEchoMethodPointcut implements Pointcut {

	public static final EchoServiceEchoMethodPointcut INSTANCE = new EchoServiceEchoMethodPointcut();

	private EchoServiceEchoMethodPointcut() {

	}

	@Override
	public ClassFilter getClassFilter() {
		return new ClassFilter() {
			@Override
			public boolean matches(Class<?> clazz) {
				return EchoService.class.isAssignableFrom(clazz); // 只要是 EchoService 的接口或子接口，或子类都可以
			}
		};
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return new MethodMatcher() {
			@Override
			public boolean matches(Method method, Class<?> targetClass) { // echo(String)
				return "echo".equals(method.getName()) &&
						method.getParameterTypes().length == 1 &&
						Objects.equals(String.class, method.getParameterTypes()[0]);
			}

			@Override
			public boolean isRuntime() {
				return false;
			}

			@Override
			public boolean matches(Method method, Class<?> targetClass, Object... args) {
				return false;
			}
		};
	}
}
