package com.zq.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 45丨层次性依赖查找：依赖查找也有双亲委派？.mp4
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-14 17:00:47
 */
public class HierarchicalDependencyLookupDemo {

	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 将当前类 HierarchicalDependencyLookupDemo 作为配置类
		applicationContext.register(HierarchicalDependencyLookupDemo.class);

		// 1.  获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
		ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
		System.out.println("当前 BeanFactory 的 Parent BeanFactory ：" + beanFactory.getParentBeanFactory());

		// 2. 设置 Parent BeanFactory
		HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
		beanFactory.setParentBeanFactory(parentBeanFactory);
		System.out.println("当前 BeanFactory 的 Parent BeanFactory ：" + beanFactory.getParentBeanFactory());

		displayContainsLocalBean(beanFactory, "user");
		displayContainsLocalBean(parentBeanFactory, "user");

		displayContainsBean(beanFactory, "user");
		displayContainsBean(parentBeanFactory, "user");

		// 启动 Spring 上下文
		applicationContext.refresh();

		// 关闭 Spring 上下文
		applicationContext.close();
	}

	private static HierarchicalBeanFactory createParentBeanFactory() {
		// 创建 BeanFactory 容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		// XML 配置文件 ClassPath 路径
		String location = "classpath:/META-INF/dependency-lookup-context.xml";
		// 加载配置
		reader.loadBeanDefinitions(location);

		return beanFactory;
	}


	private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
		System.out.printf("当前 BeanFactory[%s] 是否包含 Bean[name : %s] : %s\n", beanFactory, beanName,
				containsBean(beanFactory, beanName));
	}

	private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
		BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
		if (parentBeanFactory instanceof HierarchicalBeanFactory) {
			HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
			if (containsBean(parentHierarchicalBeanFactory, beanName)) {
				return true;
			}
		}
		return beanFactory.containsLocalBean(beanName);
	}

	private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
		System.out.printf("当前 BeanFactory[%s] 是否包含 Local Bean[name : %s] : %s\n", beanFactory, beanName,
				beanFactory.containsLocalBean(beanName));
	}
}
