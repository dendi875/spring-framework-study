package com.zq.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * 视频：206丨Spring模式注解（StereotypeAnnotations）.mp4
 * PPT:  第十八章 Spring 注解（Annotations）.pdf
 *
 * Spring 模式注解（Stereotype Annotations）
 * • @Component “派⽣性”原理
 * 		• 核心组件 - org.springframework.context.annotation.ClassPathBeanDefinitionScanner
 * 		   • org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
 * • 资源处理 - org.springframework.core.io.support.ResourcePatternResolver
 * • 资源-类元信息
 * 		• org.springframework.core.type.classreading.MetadataReaderFactory
 * • 类元信息 - org.springframework.core.type.ClassMetadata
 * 		• ASM 实现 - org.springframework.core.type.classreading.ClassMetadataReadingVisitor
 * 		• 反射实现 - org.springframework.core.type.StandardAnnotationMetadata
 * • 注解元信息 - org.springframework.core.type.AnnotationMetadata
 * 		• ASM 实现 - org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor
 * 		• 反射实现 - org.springframework.core.type.StandardAnnotationMetadata
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 19:03:14
 */
@ComponentScan(basePackages = "com.zq.annotation") // 指定扫描的 Class-Path
public class ComponentScanDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		applicationContext.register(ComponentScanDemo.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找 TestClass
		TestClass testClass = applicationContext.getBean(TestClass.class);
		System.out.println(testClass);

		// 关闭 Spring 应用上下文
		applicationContext.close();
	}
}
