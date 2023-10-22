package com.zq.aop.overview.proxy;

/**
 * {@link EchoService} 默认实现
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since 2023-10-22 16:29:58
 */
public class DefaultEchoService implements EchoService {

	@Override
	public String echo(String message) {
		return "[Echo]：" + message;
	}
}
