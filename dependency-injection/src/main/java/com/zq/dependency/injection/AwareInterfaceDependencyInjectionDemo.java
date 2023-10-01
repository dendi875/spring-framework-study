package com.zq.dependency.injection;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 *
 *  基于 {@link org.springframework.beans.factory.Aware} 接口回调的依赖注入示例
 *
 *  视频：59丨接口回调注入：回调注入的使用场景和限制有哪些？.mp4
 *
 *  PPT: 第六章 Spring 依赖注入.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 21:16:59
 */
public class AwareInterfaceDependencyInjectionDemo implements
		BeanFactoryAware, ApplicationContextAware, EnvironmentAware {

	private static BeanFactory beanFactory;

	private static ApplicationContext applicationContext;

	private static  Environment environment;

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类），也是一个 Spring Bean
		context.register(AwareInterfaceDependencyInjectionDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		System.out.println(beanFactory == context.getBeanFactory());
		System.out.println(applicationContext == context);

		System.out.println(environment == context.getEnvironment());

		// 显示地关闭 Spring 应用上下文
		context.close();
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
	}

	@Override
	public void setEnvironment(Environment environment) {
		AwareInterfaceDependencyInjectionDemo.environment = environment;
	}
}