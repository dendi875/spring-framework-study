package com.zq.resource.springx;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Java 标准资源管理扩展
 *	自定义实现
 * • 实现 URLStreamHandler
 * • 添加 -Djava.protocol.handler.pkgs 启动参数，指向 URLStreamHandler 实现类的包下
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-14 16:58:04
 */
public class Handler extends sun.net.www.protocol.x.Handler {

	// -Djava.protocol.handler.pkgs=com.zq.resource
	public static void main(String[] args) throws IOException {
		URL url = new URL("springx:///META-INF/production.properties"); // 类似于 classpath:/META-INF/default.properties
		InputStream inputStream = url.openStream();
		System.out.println(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
	}
}
