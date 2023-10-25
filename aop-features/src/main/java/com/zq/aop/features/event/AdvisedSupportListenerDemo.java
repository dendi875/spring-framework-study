package com.zq.aop.features.event;

import com.zq.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.zq.aop.overview.proxy.DefaultEchoService;
import com.zq.aop.overview.proxy.EchoService;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisedSupportListener;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 视频：65丨代理对象创建基础类 - ProxyCreatorSupport-66丨AdvisedSupport事件监听器 - AdvisedSupportListener.mp4
 *
 * AdvisedSupport 事件监听器
 * • 核心 API - org.springframework.aop.framework.AdvisedSupportListener
 * • 事件对象 - org.springframework.aop.framework.AdvisedSupport
 * • 事件来源 - org.springframework.aop.framework.ProxyCreatorSupport
 * 		• 激活事件触发 - ProxyCreatorSupport#createAopProxy
 * 		• 变更事件触发 - 代理接口变化时、 Advisor 变化时、配置复制
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-25 18:33:48
 */
public class AdvisedSupportListenerDemo {

	public static void main(String[] args) {
		// 确认目标对象（被对象对象）
		EchoService defaultEchoService = new DefaultEchoService();
		// 注入目标对象
		ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
		// 添加 advice
		proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
		// 添加事件监听器
		proxyFactory.addListener(new AdvisedSupportListener() {
			@Override
			public void activated(AdvisedSupport advised) {
				System.out.println("AOP 配置对象：" + advised + " 已激活");
			}

			@Override
			public void adviceChanged(AdvisedSupport advised) {
				System.out.println("AOP 配置对象：" + advised + " 已变化");
			}
		});
		// 获取代理对象
		// 活动事件触发 ProxyCreatorSupport#createAopProxy
		EchoService echoService = (EchoService) proxyFactory.getProxy();

		// 变更事件触发
		proxyFactory.addAdvice(new Advice() {
		});

		//System.out.println(echoService.echo("Hello, World"));
	}
}
