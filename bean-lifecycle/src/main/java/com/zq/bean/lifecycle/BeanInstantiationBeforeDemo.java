package com.zq.bean.lifecycle;

import com.zq.spring.ioc.overview.domain.SuperUser;
import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 *
 * Spring Bean 实例化前阶段
 * • 非主流生命周期 - Bean 实例化前阶段
 * 		• InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation
 *
 * 视频：93丨SpringBean实例化前阶段：Bean的实例化能否被绕开？.mp4
 * PPT: 第九章 Spring Bean生命周期（Beans Lifecycle）.pdf第九章 Spring Bean生命周期（Beans Lifecycle）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-09 11:25:35
 */
public class BeanInstantiationBeforeDemo {
	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 添加 BeanPostProcessor 实现
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

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

	static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

		@Override
		public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
				// 把配置完成的 superUser Bean 覆盖掉
				return new SuperUser();
			}
			return null; // 保持 Spring IoC 容器的实例化操作，也就是保持即有状态不变
		}
	}
}
