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
 * Spring Bean 初始化中阶段 see {@link com.zq.spring.bean.definition.BeanInitializationAndDestroyDemo }
 * • Bean 初始化（Initialization）
 * 		• @PostConstruct 标注方法
 * 		• 实现 InitializingBean 接口的 afterPropertiesSet() 方法
 * 		• 自定义初始化方法
 * 视频：99丨SpringBean初始化阶段：@PostConstruct、InitializingBean以及自定义方法.mp4
 * PPT：第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * Spring Bean 初始化后阶段
 * • 方法回调
 * 		• BeanPostProcessor#postProcessAfterInitialization
 * 视频：100丨SpringBean初始化后阶段：BeanPostProcessor.mp4
 * PPT：第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * Spring Bean 初始化完成阶段
 * • 方法回调
 * 		• Spring 4.1 +：SmartInitializingSingleton#afterSingletonsInstantiated
 * 视频：100丨SpringBean初始化后阶段：BeanPostProcessor.mp4
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
