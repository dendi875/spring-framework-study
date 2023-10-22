package com.zq.aop.overview.proxy;

/**
 * Java AOP 代理模式（Proxy）
 * • Java 静态代理
 * 		• 常用 OOP 继承和组合相结合
 *
 * 视频：08丨Java AOP代理模式（Proxy）：Java静态代理和动态代理的区别是什么？.mp4
 * PPT:  第一章 - Spring AOP 总览.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-22 16:28:37
 */
public class StaticProxyDemo {
	public static void main(String[] args) {
		EchoService echoService = new ProxyEchoService(new DefaultEchoService());
		echoService.echo("Hello, World");
	}
}
