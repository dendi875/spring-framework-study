package com.zq.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @AliasFor 即可以做显示别名，又可以做隐式别名，还可以做传递性别名
 *
 * 参考 @SpringApplication.scanBasePackages 功能来做示例
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 20:48:44
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan
public @interface MyComponentScan {

	// 1. 这里就是隐性别名，可以这样理解：子注解 @MyComponentScan 它提供了新的属性方法 scanBasePackages() 引用了
	// 父（元）注解 @ComponentScan中的属性方法 basePackages()
	// 2. attribute 换成 value 也行，因为：
	//  @MyComponentScan.scanBasePackages -> @AliasFor
	//  @ComponentScan.basePackages -> @AliasFor
	//  @ComponentScan.value，这就是传递了隐性别名

	//@AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
	@AliasFor(annotation = ComponentScan.class, value = "basePackages")
	String[] scanBasePackages() default {};
}
