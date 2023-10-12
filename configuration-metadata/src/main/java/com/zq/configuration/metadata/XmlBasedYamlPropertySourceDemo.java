package com.zq.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 *
 * 基于 XML 资源的 YAML 外部化配置示例
 *
 * 视频：121丨基于YAML资源装载外部化配置.mp4
 * PPT：第十章 Spring 配置元信息（Configuration Metadata）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-12 20:56:52
 */
public class XmlBasedYamlPropertySourceDemo {
	public static void main(String[] args) {
		// 创建 IoC 容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 创建 XML 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		// 加载 XML 资源
		reader.loadBeanDefinitions("classpath:/META-INF/yaml-property-source-context.xml");

		// 获取 Map Yaml 对象
		Map<String, Object> yamlMap = beanFactory.getBean("yamlMap", Map.class);
		System.out.println(yamlMap);
	}
}
