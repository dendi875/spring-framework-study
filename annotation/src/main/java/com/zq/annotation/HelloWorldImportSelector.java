package com.zq.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 *
 * @EnableHelloWorld 模块驱动的 {@link ImportSelector} 实现
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 22:50:56
 */
public class HelloWorldImportSelector implements ImportSelector{

	// 通过注解参数选择零个或一个或多个导入类
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.zq.annotation.HelloWorldConfiguration"};
	}
}
