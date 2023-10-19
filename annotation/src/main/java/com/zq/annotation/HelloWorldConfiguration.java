package com.zq.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @EnableHelloWorld 的 Configuration Class 方式实现
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 22:43:34
 */
@Configuration
public class HelloWorldConfiguration {

	@Bean
	public String helloWorld() {
		return "Hello, World";
	}
}
