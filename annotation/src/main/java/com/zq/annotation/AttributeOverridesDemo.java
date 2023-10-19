package com.zq.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 视频：209丨Spring注解属性覆盖（AttributeOverrides）.mp4
 * PPT: 第十八章 Spring 注解（Annotations）.pdf
 *
 * Spring 注解属性覆盖示例
 *
 * 1. 隐性覆盖
 * One、Two 两个注解，且两个注解有一个同名的属性，One 注解标注在 Two 注解上，那么 Tow 注解就会覆盖 One 上同名的属性
 * 2. 显性覆盖
 * A 注解 AliasFor B 注解后，A 就会覆盖 B 上同名的属性
 * 3. 传递性覆盖
 * 类似属性别名的传递性，有层次关系
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 21:20:12
 */
//@MyComponentScan2(scanBasePackages = "com.zq.annotation") // 属性覆盖：隐性覆盖
@MyComponentScan2(packages = "com.zq.annotation") // 属性覆盖：显性覆盖
public class AttributeOverridesDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册 Configuration Class
		applicationContext.register(AttributeOverridesDemo.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找 TestClass
		TestClass testClass = applicationContext.getBean(TestClass.class);
		System.out.println(testClass);

		// 关闭 Spring 应用上下文
		applicationContext.close();
	}
}
