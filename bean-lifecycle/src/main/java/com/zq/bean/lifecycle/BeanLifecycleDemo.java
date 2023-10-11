package com.zq.bean.lifecycle;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 *
 * Spring Bean 销毁前阶段
 * • 方法回调
 * 		• DestructionAwareBeanPostProcessor#postProcessBeforeDestruction
 * 视频：102丨SpringBean销毁前阶段：DestructionAwareBeanPostProcessor用在怎样的场景.mp4
 * PPT: 第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * Spring Bean 销毁阶段 see {@link com.zq.spring.bean.definition.BeanInitializationAndDestroyDemo }
 * • Bean 销毁（Destroy）
 * 		• @PreDestroy 标注方法
 * 		• 实现 DisposableBean 接口的 destroy() 方法
 * 		• 自定义销毁方法
 *
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-11 10:30:18
 */
public class BeanLifecycleDemo {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		// 添加 MyDestructionAwareBeanPostProcessor 执行销毁前回调
		beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
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

		// 执行 Bean 销毁，它只是代表在容器内被销毁，但不代表被整个Java进程被GC给GC了
		beanFactory.destroyBean("userHolder", userHolder);
		// Bean 销毁并不意味着 Bean 垃圾回收了
		System.out.println(userHolder);
	}
}
