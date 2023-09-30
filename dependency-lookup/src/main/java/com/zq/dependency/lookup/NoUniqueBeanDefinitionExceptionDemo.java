package com.zq.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.NoUniqueBeanDefinitionException} 示例代码
 *
 * 视频：49丨依赖查找中的经典异常：Bean找不到？Bean不是唯一的？Bean创建失败？.mp4
 *
 * PPT：第五章 Spring 依赖查找.pdf
 *
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 17:30:16
 */
public class NoUniqueBeanDefinitionExceptionDemo {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 将当前类 NoUniqueBeanDefinitionExceptionDemo 作为配置类（Configuration Class）
		applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
		// 启动应用上下文
		applicationContext.refresh();

		try {
			// 由于 Spring 应用上下文存在两个 String 类型的 Bean，通过单一类型查找会抛出异常
			applicationContext.getBean(String.class);
		} catch (NoUniqueBeanDefinitionException e) {
			System.err.printf(" Spring 应用上下文存在%d个 %s 类型的 Bean，具体原因：%s%n",
					e.getNumberOfBeansFound(),
					String.class.getName(),
					e.getMessage());
		}

		// 关闭应用上下文
		applicationContext.close();
	}

	@Bean
	public String bean1() {
		return "1";
	}

	@Bean
	public String bean2() {
		return "2";
	}

	@Bean
	public String bean3() {
		return "3";
	}
}
