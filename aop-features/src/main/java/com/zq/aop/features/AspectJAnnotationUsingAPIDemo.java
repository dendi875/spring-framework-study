package com.zq.aop.features;

import com.zq.aop.features.aspect.AspectConfiguration;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 通过编程方式来创建出一个 AspectJ 代理对象
 *
 *编程方式创建 @AspectJ 代理
 * • 实现类
 * 		• org.springframework.aop.aspectj.annotation.AspectJProxyFactory
 *
 * 视频：24丨编程方式创建 @AspectJ代理.mp4
 *
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-23 14:49:24
 */
public class AspectJAnnotationUsingAPIDemo {

	public static void main(String[] args) {

		// 定义一个 HashMap，作为被代理的对象
		Map<String, Object> cache = new HashMap<>();

		// 创建代理工厂
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache); // 把被代理的对象传进去
		// 增加 Aspect 配置类
		proxyFactory.addAspect(AspectConfiguration.class);
		// 添加 beforeAdvice，添加之后框架会帮我们自动触发这个方法的调用
		proxyFactory.addAdvice(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				if ("put".equals(method.getName()) && args.length == 2) {
					System.out.printf("[MethodBefore] 当前存放的是 Key: %s, Value: %s\n", args[0], args[1]);
				}
			}
		});

		// 存储数据
		// cache.put("1", "A"); // cache 是被代理对象所以不会触发 beforeAdvice，但我们需要使用生成的代理对象去操作
		Map<String, Object> proxy = proxyFactory.getProxy();
		// 使用代理对象操作
		proxy.put("1", "A");

		System.out.println(cache.get("1"));
	}
}
