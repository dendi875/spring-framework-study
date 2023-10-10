package com.zq.bean.lifecycle;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Spring Bean 初始化前阶段
 * • 方法回调
 * 		• BeanPostProcessor#postProcessBeforeInitialization
 * 视频：98丨SpringBean初始化前阶段：BeanPostProcessor.mp4
 * PPT：第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-10 21:02:38
 */
public class BeanInitializationLifecycleDemo {
	public static void main(String[] args) {

		executeBeanFactory();

	}

	private static void executeBeanFactory() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		// 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
		int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
		System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);
		// 显示地执行 preInstantiateSingletons()
		// SmartInitializingSingleton 通常在 Spring ApplicationContext 场景使用
		// preInstantiateSingletons 将已注册的 BeanDefinition 初始化成 Spring Bean
		beanFactory.preInstantiateSingletons();

		// 通过 Bean Id 和类型进行依赖查找
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println(superUser);

		// 构造器注入按照类型注入，resolveDependency
		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

		System.out.println(userHolder);

	}
}
