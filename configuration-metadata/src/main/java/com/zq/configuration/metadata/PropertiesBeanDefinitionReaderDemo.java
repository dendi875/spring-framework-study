package com.zq.configuration.metadata;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 *
 * 基于 XML 资源装载 Spring Bean 配置元信息 {@link com.zq.spring.ioc.overview.container.BeanFactoryAsIoCContainerDemo }
 *
 *
 *
 * 基于 Properties 资源装载 Spring Bean 配置元信息
 * 	* 底层实现 - PropertiesBeanDefinitionReader
 *
 * 视频：111丨基于Properties资源装载Spring Bean配置元信息：为什么Spring官方不推荐？.mp4
 * PPT: 第十章 Spring 配置元信息（Configuration Metadata）.pdf---基于 Properties 资源装载 Spring Bean 配置元信息
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-11 16:18:46
 */
public class PropertiesBeanDefinitionReaderDemo {

	public static void main(String[] args) {
		// 创建 IoC 底层容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 创建面向 Properties 资源的 BeanDefinitionReader 示例
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
		// Properties 资源加载默认通过 ISO-8859-1，实际存储 UTF-8
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		// 通过指定的 ClassPath 获取 Resource 对象
		Resource resource = resourceLoader.getResource("classpath:/META-INF/user-bean-definitions.properties");
		// 转换成带有字符编码 EncodedResource 对象
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
		System.out.println(String.format("已记载 %d 个 BeanDefinition\n", beanDefinitionsCount));
		// 通过依赖查找获取 User Bean
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);
	}
}
