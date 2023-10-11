package com.zq.configuration.metadata;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 *
 * see {@link com.zq.spring.bean.definition.BeanDefinitionCreationDemo }
 * see {@link com.zq.spring.bean.definition.BeanDefinitionRegisterDemo }
 *
 * Spring Bean 属性元信息
 * • Bean 属性元信息 - PropertyValues
 * 		• 可修改实现 - MutablePropertyValues
 * 		• 元素成员 - PropertyValue
 * • Bean 属性上下文存储 - AttributeAccessor
 * • Bean 元信息元素 - BeanMetadataElement
 *
 * 视频：108丨Spring Bean属性元信息：PropertyValues.mp4
 * PPT: 第十章 Spring 配置元信息（Configuration Metadata）.pdf
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-11 15:10:37
 */
public class BeanConfigurationMetadataDemo {
	public static void main(String[] args) {

		// BeanDefinition 的定义（声明）
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		beanDefinitionBuilder.addPropertyValue("name", "zhangquan");
		// 获取 AbstractBeanDefinition
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
		// 附加属性（不影响 Bean 实例化、属性赋值、初始化；populate、initialize）
		beanDefinition.setAttribute("name", "张权");
		// 当前 BeanDefinition 来自于何方（辅助作用）
		beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// Spring Bean 生命周期：初始化后阶段
		beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
					BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
					if (BeanConfigurationMetadataDemo.class.equals(bd.getSource())) { // 通过 source 判断来
						// 属性（存储）上下文
						String name = (String) bd.getAttribute("name"); // 就是 "小马哥"
						User user = (User) bean;
						user.setName(name);
					}
				}
				return bean;
			}
		});

		// 注册 User 的 BeanDefinition
		beanFactory.registerBeanDefinition("user", beanDefinition);

		User user = beanFactory.getBean("user", User.class);

		System.out.println(user);

	}
}
