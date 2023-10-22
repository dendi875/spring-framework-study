package com.zq.aop.overview.proxy;

/**
 * 静态代理实现观察方法执行耗时
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-22 16:31:19
 */
public class ProxyEchoService implements EchoService {

	private final EchoService echoService;

	public ProxyEchoService(EchoService echoService) {
		this.echoService = echoService;
	}

	@Override
	public String echo(String message) {
		long st = System.currentTimeMillis();
		String rt = echoService.echo(message);
		long duration = System.currentTimeMillis() - st;
		System.out.println("方法执行耗时：" + duration + "ms");

		return rt;
	}
}
