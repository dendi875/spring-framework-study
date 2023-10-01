package com.zq.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 *
 * 依赖来源示例
 *
 * 入口 - {@link org.springframework.beans.factory.support.DefaultListableBeanFactory#resolveDependency}
 *
 * 视频：72丨依赖注入的来源：难道依赖注入的来源与依赖查找的不同吗？.mp4
 * PPT：第七章 Spring IoC依赖来源.pdf
 * @author <quanzhang875@gmail.com>
 * @since  2023-10-01 16:35:56
 */
public class DependencySourceDemo {

	// 依赖注入在 postProcessProperties 方法中执行，早于 setter方法注入，早于 @PostConstruct 注入
	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@PostConstruct
	public void initByInjection() {
		System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext));
		System.out.println("beanFactory == applicationContext.getBeanFactory() " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
		System.out.println("resourceLoader == applicationContext " + (resourceLoader == applicationContext));
		System.out.println("ApplicationEventPublisher == applicationContext " + (applicationEventPublisher == applicationContext));
	}

	@PostConstruct
	public void initByLookup() {
		getBean(BeanFactory.class);
		getBean(ResourceLoader.class);
		getBean(ApplicationContext.class);
		getBean(ApplicationEventPublisher.class);
	}

	private <T> T getBean(Class<T> beanType) {
		try {
			return beanFactory.getBean(beanType);
		} catch (NoSuchBeanDefinitionException e) {
			System.err.println("当前类型" + beanType.getName() + " 无法在 BeanFactory 中查找!");
		}
		return null;
	}

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类） -> Spring Bean
		applicationContext.register(DependencySourceDemo.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找 DependencySourceDemo Bean
		DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);

		// 显示地关闭 Spring 应用上下文
		applicationContext.close();
	}
}
