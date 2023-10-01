package com.zq.dependency.injection;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 *
 *  基于 Java 注解的依赖 方法 注入示例
 *
 *  视频：58丨方法注入：方法注入是@Autowired专利吗？.mp4
 *
 *  PPT: 第六章 Spring 依赖注入.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 21:16:59
 */
public class AnnotationDependencyMethodInjectionDemo {

	private UserHolder userHolder;

	private UserHolder userHolder2;

	@Autowired
	public void init1(UserHolder userHolder) {
		this.userHolder = userHolder;
	}

	@Resource
	public void init2(UserHolder userHolder2) {
		this.userHolder2 = userHolder2;
	}

	@Bean
	public UserHolder userHolder(User user) {
		return new UserHolder(user);
	}

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类），也会当作一个 Spring Bean
		applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
		// 加载 XML 资源，解析并且生成 BeanDefinition
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找前面注册的当前配置类的 Spring Bean
		AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

		// @Autowired 字段关联
		UserHolder userHolder = demo.userHolder;
		System.out.println(userHolder);
		System.out.println(demo.userHolder2);

		System.out.println(userHolder == demo.userHolder2);

		// 显示地关闭 Spring 应用上下文
		applicationContext.close();
	}
}