package com.zq;

import com.zq.spring.ioc.overview.container.BeanFactoryAsIoCContainerDemo;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 *
 * Spring 元信息解析阶段--面向注解 BeanDefinition 解析
 *
 * Spring Bean 元信息解析阶段
 * 	• 面向资源 BeanDefinition 解析
 * 		• BeanDefinitionReader
 * 		• XML 解析器 - BeanDefinitionParser {@link BeanFactoryAsIoCContainerDemo}
 * 	• 面向注解 BeanDefinition 解析
 * 		• AnnotatedBeanDefinitionReader
 *
 * 视频：89丨SpringBean元信息解析阶段：BeanDefinition的解析.mp4
 * PPT：第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-10-07 17:29:03
 */
public class BeanMetadataParsingDemo {
	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 基于 Java 注解的 AnnotatedBeanDefinitionReader 的实现
		AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
		int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
		// 注册当前类（并不一定要是 @Component 标注的class）
		beanDefinitionReader.register(BeanMetadataParsingDemo.class);
		int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
		int beanDefinitionCount = beanDefinitionCountAfter - beanDefinitionCountBefore;
		System.out.println("已加载 BeanDefinition 数量：" + beanDefinitionCount);
		// 普通的 Class 作为 Component 注册到 Spring IoC 容器后，通常 Bean 名称为 beanMetadataParsingDemo
		// Bean 名称生成来自于 BeanNameGenerator，注解实现 AnnotationBeanNameGenerator
		BeanMetadataParsingDemo demo = beanFactory.getBean("beanMetadataParsingDemo",
				BeanMetadataParsingDemo.class);
		System.out.println(demo);
	}
}
