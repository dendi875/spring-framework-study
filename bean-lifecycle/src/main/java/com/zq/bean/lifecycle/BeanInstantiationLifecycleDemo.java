package com.zq.bean.lifecycle;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Bean 实例化生命周期
 *
 * Spring Bean 实例化前阶段
 *  • 非主流生命周期 - Bean 实例化前阶段
 *  	• InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation
 *
 *  视频：93丨SpringBean实例化前阶段：Bean的实例化能否被绕开？.mp4
 *  PPT: 第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * Spring Bean 实例化阶段
 * • 实例化方式
 * 		• 传统实例化方式
 * 			• 实例化策略 - InstantiationStrategy
 * 		• 构造器依赖注入
 *	视频：94丨SpringBean实例化阶段：Bean实例是通过Java反射创建吗？.mp4
 *  PPT: 第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * Spring Bean 实例化后阶段
 * • Bean 属性赋值（Populate）判断
 * 		• InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation
 *  视频：95丨SpringBean实例化后阶段：Bean实例化后是否一定被是使用吗？.mp4
 *  PPT: 第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * Spring Bean 属性赋值前阶段
 * • Bean 属性值元信息
 * 		• PropertyValues
 * • Bean 属性赋值前回调
 * 		• Spring 1.2 - 5.0：InstantiationAwareBeanPostProcessor#postProcessPropertyValues
 * 		• Spring 5.1：      InstantiationAwareBeanPostProcessor#postProcessProperties
 *
 * 视频：96丨SpringBean属性赋值前阶段：配置后的PropertyValues还有机会修改吗？.mp4
 * PPT: 第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * Spring Bean Aware接口回调阶段
 * 	视频：97丨Aware接口回调阶段：众多Aware接口回调的顺序是安排的？.mp4
 * 	PPT: 第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 *
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-10 19:03:41
 */
public class BeanInstantiationLifecycleDemo {
	public static void main(String[] args) {
		executeBeanFactory();

		System.out.println("--------------------------------");

		executeApplicationContext();
	}

	private static void executeBeanFactory() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 方法一：添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
		// beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		// 方法二：将 MyInstantiationAwareBeanPostProcessor 作为 Bean 注册

		// 基于 XML 资源 BeanDefinitionReader 实现
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
		int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
		System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);
		// 通过 Bean Id 和类型进行依赖查找
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println(superUser);

		// 构造器注入按照类型注入，resolveDependency
		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);
	}


	private static void executeApplicationContext() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
		String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
		applicationContext.setConfigLocations(locations);
		// 启动应用上下文
		applicationContext.refresh();

		User user = applicationContext.getBean("user", User.class);
		System.out.println(user);

		User superUser = applicationContext.getBean("superUser", User.class);
		System.out.println(superUser);

		// 构造器注入按照类型注入，resolveDependency
		UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);

		// 关闭应用上下文
		applicationContext.close();

	}
}
