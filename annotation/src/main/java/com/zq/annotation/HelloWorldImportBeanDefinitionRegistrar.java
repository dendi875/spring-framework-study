package com.zq.annotation;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @EnableHelloWorld 模块驱动的 {@link ImportBeanDefinitionRegistrar} 实现方式
 * @since 2023-10-19 22:54:36
 */
public class HelloWorldImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	// 把 Configuration Class 也就是 HelloWorldConfiguration 变成一个 BeanDefinition 注册到 BeanFactory 里去了
	// 从而会注册 helloWorld 这个Bean
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
										BeanDefinitionRegistry registry) {

		AnnotatedGenericBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(HelloWorldConfiguration.class);
		BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
	}
}
