package com.zq.bean.lifecycle;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 *
 * Spring BeanDefinition 合并阶段示例
 *
 * • BeanDefinition 合并
 * 		• 父子 BeanDefinition 合并
 * 			• 当前 BeanFactory 查找
 * 			• 层次性 BeanFactory 查找
 *
 *
 * 视频：91丨SpringBeanDefinition合并阶段：BeanDefinition合并过程是怎样出现的？.mp4
 * PPT：第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-07 18:06:37
 */
public class MergedBeanDefinitionDemo {
	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 基于 XML 资源 BeanDefinitionReader 实现
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		String location = "META-INF/dependency-lookup-context.xml";
		// 基于 ClassPath 加载 XML 资源
		Resource resource = new ClassPathResource(location);
		// 指定字符编码 UTF-8
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);
		System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);
		// 通过 Bean Id 和类型进行依赖查找
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println(superUser);
	}
}
