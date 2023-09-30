package com.zq.spring.ioc.overview.container;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * XML 通过 BeanFactory 应用的示例
 *
 * BeanFactory 作为 IoC 底层的容器，并没有像 ApplicationContext 那样有事件、资源管理等高级功能特性
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-04 09:52:41
 */
public class BeanFactoryAsIoCContainerDemo {
	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		// XML 配置文件 ClassPath 路径
		String location = "classpath:/META-INF/dependency-lookup-context.xml";
		// 加载配置
		int definitions = reader.loadBeanDefinitions(location);
		System.out.println("Bean 定义加载的数量：" + definitions);

		// 依赖查找方式找集合对象
		lookupCollectionByType(beanFactory);
	}

	private static void lookupCollectionByType(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("查找到的所有的 User 集合对象：" + users);
		}
	}
}
