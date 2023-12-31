package com.zq.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 API 实现依赖 Constructor 方法注入示例
 *
 *  *  视频：56丨构造器依赖注入：官方为什么推荐使用构造器注入？.mp4
 *  *
 *  *  PPT: 第六章 Spring 依赖注入.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 21:19:33
 */
public class ApiDependencyConstructorInjectionDemo {

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 生成 UserHolder 的 BeanDefinition
		BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();
		// 注册 UserHolder 的 BeanDefinition
		applicationContext.registerBeanDefinition("userHolder", userHolderBeanDefinition);

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
		// 加载 XML 资源，解析并且生成 BeanDefinition
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找并且创建 Bean
		UserHolder userHolder = applicationContext.getBean(UserHolder.class);
		System.out.println(userHolder);

		// 显示地关闭 Spring 应用上下文
		applicationContext.close();
	}

	/**
	 * 为 {@link UserHolder} 生成 {@link BeanDefinition}
	 *
	 * @return
	 */
	private static BeanDefinition createUserHolderBeanDefinition() {
		BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
		definitionBuilder.addConstructorArgReference("superUser");
		return definitionBuilder.getBeanDefinition();
	}

//    @Bean
//    public UserHolder userHolder(User user) { // superUser -> primary = true
//        UserHolder userHolder = new UserHolder();
//        userHolder.setUser(user);
//        return userHolder;
//    }
}