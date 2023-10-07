package com.zq;

import com.zq.spring.ioc.overview.container.BeanFactoryAsIoCContainerDemo;
import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Spring 元信息配置阶段--BeanDefinition 配置--面向资源--Properties 资源配置
 *
 * BeanDefinition 配置
 * 	• 面向资源
 * 		• XML 配置，使用 XmlBeanDefinitionReader，看 {@link BeanFactoryAsIoCContainerDemo}
 * 		• Properties 资源配置
 * 	• 面向注解
 * 	• 面向 API
 *
 * 视频：88丨SpringBean元信息配置阶段：BeanDefinition配置与扩展.mp4
 * PPT:第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-10-07 14:00:09
 */
public class BeanMetadataConfigurationDemo {
	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 实例化基于 Properties 资源 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		// 基于 ClassPath 加载 properties 资源
		Resource resource = new ClassPathResource("META-INF/user.properties");
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);
		System.out.println("已加载的 BeanDefinition 数量：" + beanNumbers);
		// 通过 Bean id 和类型进行依赖查找
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);
	}
}
