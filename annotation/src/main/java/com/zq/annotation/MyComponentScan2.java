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
@MyComponentScan
public @interface MyComponentScan2 {

	// 1. 这里就是隐性别名，可以这样理解：子注解 @MyComponentScan2 它提供了新的属性方法 basePackages() 引用了
	// 父（元）注解 @MyComponentScan 方法 scanBasePackages()
	// 2.还是传递了隐性别名 ，因为：
	//  @MyComponentScan2.basePackages -> @AliasFor
	//  @MyComponentScan.scanBasePackages -> @AliasFor
	//  @ComponentScan.basePackages -> @AliasFor

	@AliasFor(annotation = MyComponentScan.class, value = "scanBasePackages") // 隐性别名
	String[] basePackages() default {};

	// 与元（父）注解有相同的属性 scanBasePackages，所以它隐式的覆盖了元注解的属性，类似子类继承了父类然后覆盖了父类的方法
	String[] scanBasePackages() default {};

	// 1. 注解 MyComponentScan2.packages  AliasFor MyComponentScan2.scanBasePackages，
	// 那么 MyComponentScan2.packages 就覆盖了 MyComponentScan2.scanBasePackages 属性
	// 2. MyComponentScan 注解中也有 scanBasePackages 属性
	// 那么 MyComponentScan2.packages 也会覆盖 @MyComponentScan.scanBasePackages 属性
	// 所以 MyComponentScan2.packages 即覆盖了同注解中的 MyComponentScan2.scanBasePackages 属性
	// 又覆盖了元注解（父注解）中的 @MyComponentScan.scanBasePackages 属性
	@AliasFor("scanBasePackages")
	String[] packages() default {};
}
