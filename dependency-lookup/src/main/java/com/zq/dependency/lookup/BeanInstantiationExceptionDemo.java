package com.zq.dependency.lookup;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link org.springframework.beans.BeanInstantiationException} 实例
 *
 * 视频：49丨依赖查找中的经典异常：Bean找不到？Bean不是唯一的？Bean创建失败？.mp4
 * PPT：第五章 Spring 依赖查找.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 17:32:57
 */
public class BeanInstantiationExceptionDemo {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册 BeanDefinition Bean Class 是一个 CharSequence 接口
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
		applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

		// 启动应用上下文
		applicationContext.refresh();

		// 关闭应用上下文
		applicationContext.close();
	}
}
