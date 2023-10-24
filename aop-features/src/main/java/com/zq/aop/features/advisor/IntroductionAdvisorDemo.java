package com.zq.aop.features.advisor;

import com.zq.aop.overview.proxy.EchoService;
import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * IntroductionAdvisor
 * 1. 主要是用来限制/扩展接口的代理，比如有个类 C，它实现了两个接口X,Y，我需要限制只代理一个接口，这时就可以通过 Introduction 来操作
 * 或者我需要扩展一个Z接口
 * 2. 它只支持来过滤类型（ClassFilter getClassFilter()）
 *
 * 视频：55丨Introduction与Advice连接器 - IntroductionAdvisor.mp4
 *
 * Introduction 与 Advice 连接器
 * • 接口 - org.springframework.aop.IntroductionAdvisor
 * • 元信息
 * 		• org.springframework.aop.IntroductionInfo
 * • 通用实现
 * 		• org.springframework.aop.support.DefaultIntroductionAdvisor
 * • AspectJ 实现
 * 		• org.springframework.aop.aspectj.DeclareParentsAdvisor
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-24 17:48:14
 */
public class IntroductionAdvisorDemo implements EchoService, Comparable<IntroductionAdvisorDemo> {

	public static void main(String[] args) {
		// 1. 确认目标对象（代理对象）
		IntroductionAdvisorDemo target = new IntroductionAdvisorDemo();

		// 2. 注入目标对象
		ProxyFactory proxyFactory = new ProxyFactory(target);
		//ProxyFactory proxyFactory = new ProxyFactory();
		//proxyFactory.setTarget(target);

		// 3. 添加 Advisor
		proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(new MethodBeforeAdvice() {  // 假如添加 MethodBeforeAdvice
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				System.out.println("BeforeAdvice: " + method);
			}
		}, new IntroductionInfo() {
			@Override
			public Class<?>[] getInterfaces() {
				return new Class[]{EchoService.class, Map.class};
			}
		}));

		// 4. 获取目标对象
		Object proxy = proxyFactory.getProxy();

		// 转成 EchoService
		EchoService echoService = (EchoService) proxy;
		echoService.echo("Hello, World");

		// 转成 Map
		Map map = (Map) proxy;
		map.put("1", "A");

		// 转成 Comparable
		Comparable comparable = (Comparable) proxy;
		comparable.compareTo(null);

	}

	@Override
	public String echo(String message) throws NullPointerException {
		return "IntroductionAdvisorDemo : " + message;
	}

	@Override
	public int compareTo(IntroductionAdvisorDemo o) {
		return 0;
	}
}
