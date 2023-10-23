package com.zq.aop.features.aspect;

/**
 * 基于 XML 配置 Advice
 *
 * @author <a href="mailto:zhangquan@hupu.com">zhangquan</a>
 * @since  2023-10-23 17:43:15
 */
public class AspectXmlConfig {

	public void beforeAnyPublicMethod() {
		System.out.println("@Before any public method.");
	}
}
