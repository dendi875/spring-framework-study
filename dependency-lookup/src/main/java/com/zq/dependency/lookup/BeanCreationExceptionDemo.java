package com.zq.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 *
 * {@link org.springframework.beans.factory.BeanCreationException} 示例
 *
 * 视频：49丨依赖查找中的经典异常：Bean找不到？Bean不是唯一的？Bean创建失败？.mp4
 *
 * PPT：第五章 Spring 依赖查找.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 17:35:22
 */
public class BeanCreationExceptionDemo {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 注册 BeanDefinition Bean Class 是一个 POJO 普通类，不过初始化方法回调时抛出异常
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
		applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

		// 启动应用上下文
		applicationContext.refresh();

		// 关闭应用上下文
		applicationContext.close();
	}

	static class POJO implements InitializingBean {

		@PostConstruct // CommonAnnotationBeanPostProcessor
		public void init() throws Throwable {
			throw new Throwable("init() : For purposes...");
		}

		@Override
		public void afterPropertiesSet() throws Exception {
			throw new Exception("afterPropertiesSet() : For purposes...");
		}
	}

}
