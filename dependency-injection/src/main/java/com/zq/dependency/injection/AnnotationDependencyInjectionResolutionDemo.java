package com.zq.dependency.injection;

import com.zq.dependency.injection.annotation.MyAutowired;
import com.zq.dependency.injection.annotation.MyInject;
import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 *
 * 注解驱动的依赖注入处理过程
 *
 *	基础知识
 *		• 入口 - DefaultListableBeanFactory#resolveDependency
 *		• 依赖描述符 - DependencyDescriptor
 *		• 自定绑定候选对象处理器 - AutowireCandidateResolver
 *		* AutowiredAnnotationBeanPostProcessor
 *		* CommonAnnotationBeanPostProcessor
 *
 *
 * 视频：65丨依赖处理过程：依赖处理时会发生什么？其中与依赖查找的差异在哪？.mp4
 * PPT：第六章 Spring 依赖注入.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-10-01 14:53:06
 */
public class AnnotationDependencyInjectionResolutionDemo {

	@Autowired          // 依赖查找（处理） + 延迟
	@Lazy
	private User lazyUser;

	// DependencyDescriptor ->
	// 必须（required=true）
	// 实时注入（eager=true)
	// 通过类型（User.class）
	// 字段名称（"user"）
	// 是否首要（primary = true)
	@Autowired          // 依赖查找（处理）
	private User user;

	@MyAutowired
	private User myAutowiredUser;

	@Autowired          // 集合类型依赖注入
	private Map<String, User> users; // user superUser

	@Autowired
	private Optional<User> userOptional; // superUser

	@Inject
	private User userInject;

	@MyInject
	private User myInjectUser;

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类） -> Spring Bean
		applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

		String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
		// 加载 XML 资源，解析并且生成 BeanDefinition
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
		AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

		// 期待输出 superUser Bean
		System.out.println("demo.user = " + demo.user);
		System.out.println("demo.myAutowiredUser = " + demo.myAutowiredUser);
		System.out.println("demo.userInject = " + demo.userInject);
		System.out.println("demo.myInjectUser = " + demo.myInjectUser);
		System.out.println("demo.lazyUser = " + demo.lazyUser);

		// 期待输出 user superUser
		System.out.println("demo.users = " + demo.users);
		// 期待输出 superUser Bean
		System.out.println("demo.userOptional = " + demo.userOptional);


		// 显示地关闭 Spring 应用上下文
		applicationContext.close();
	}

	//@Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    //public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
    //    AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
    //    // @Autowired + @Inject +  新注解 @MyInject
    //    Set<Class<? extends Annotation>> autowiredAnnotationTypes =
    //            new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, MyInject.class));
    //    beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
    //    return beanPostProcessor;
    //}

	@Bean
	@Order(Ordered.LOWEST_PRECEDENCE - 3)
	@Scope
	public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
		AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
		beanPostProcessor.setAutowiredAnnotationType(MyInject.class);
		return beanPostProcessor;
	}
}
