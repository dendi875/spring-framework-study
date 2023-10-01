package com.zq.dependency.injection;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * {@link org.springframework.beans.factory.ObjectProvider} 延迟依赖注入
 *
 * 视频：64丨延迟依赖注入：如何实现延迟执行依赖注入？与延迟依赖查找是类似的吗？.mp4
 * PPT: 第六章 Spring 依赖注入.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-10-01 14:03:07
 */
public class LazyAnnotationDependencyInjectionDemo {

	@Autowired
	private User user; // 实时注入

	@Autowired
	private ObjectProvider<User> objectProviderUser; // 延时注入

	@Autowired
	private ObjectFactory<User> objectFactoryUser; // 延时注入

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类），也会当作一个 Spring Bean
		applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

		// 加载 XML 资源，解析并且生成 BeanDefinition
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找前面注册的当前配置类的 Spring Bean
		LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

		System.out.println("demo.user = " + demo.user); // 实时注入
		System.out.println("demo.objectProviderUser = " + demo.objectProviderUser.getObject()); // 延时注入
		demo.objectProviderUser.forEach(System.out::println);

		System.out.println("demo.objectFactoryUser = " + demo.objectFactoryUser.getObject());

		// 显示地关闭 Spring 应用上下文
		applicationContext.close();
	}
}
