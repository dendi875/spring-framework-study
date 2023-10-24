package com.zq.aop.features;

import org.springframework.aop.framework.ProxyFactory;

import java.util.Random;

/**
 * 视频：51丨Joinpoint After Advice标准实现.mp4
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since 2023-10-24 16:10:34
 */
public class ThrowAdviceDemo {

	public static void main(String[] args) {
		// 确认目标对象（被代理对象）
		ThrowAdviceDemo adviceDemo = new ThrowAdviceDemo();

		// 注入目标对象
		ProxyFactory proxyFactory = new ProxyFactory(adviceDemo);

		// 添加 Advice
		proxyFactory.addAdvice(new MyThrowsAdvice());

		// 获取代理对象
		ThrowAdviceDemo proxy = (ThrowAdviceDemo) proxyFactory.getProxy();
		proxy.execute();
		proxy.execute();
	}

	public void execute() {
		Random random = new Random();
		if (random.nextBoolean()) {
			throw new RuntimeException("ThrowAdviceDemo.execute 故意抛出异常");
		}
		System.out.println("executing...");
	}
}
