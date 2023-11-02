package com.zq.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 实现一个 HelloWorld 的 Enable 模块驱动
 *
 *  第一步：通过 @EnableXXX 命名
 *  第二步：通过 @Import 导入具体实现
 *
 *  第二步的具体实现又分为三种方式：
 *  方式一：通过 Configuration Class 实现，比如 @EnableWebMvc
 *  方式二：实现 ImportSelector 接口，比如 @EnableTransactionManagement
 *  方式三：实现 ImportBeanDefinitionRegistrar 接口，比如 @EnableAspectJAutoProxy
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-19 22:38:47
 */
// @Import(HelloWorldConfiguration.class) // 第二步：方式一
@Import(HelloWorldImportSelector.class) // 第二步：方式二
// @Import(HelloWorldImportBeanDefinitionRegistrar.class) // 第二步：方式三
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableHelloWorld {
}
