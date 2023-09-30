package com.zq.spring.bean.definition;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition}
 *
 * 33丨命名Spring Bean：id和name属性命名Bean，哪个更好？.mp4
 *
 * 创建 BeanDefinition 的两种方式：
 * 第一种：通过 BeanDefinitionBuilder
 * 第二种：通过 AbstractBeanDefinition 以及派生类
 *
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-04 11:18:08
 */
public class BeanDefinitionCreationDemo {

	public static void main(String[] args) {
		createByBeanDefinitionBuilder();
		createByAbstractBeanDefinition();
	}

	/**
	 * 通过 BeanDefinitionBuilder 创建 BeanDefinition
	 */
	private static void createByBeanDefinitionBuilder() {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		// 设置属性
		beanDefinitionBuilder
				.addPropertyValue("id", 1)
				.addPropertyValue("name", "小张");
		// 获取 BeanDefinition 实例
		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
		// 这里的 beanDefinition 并非 Bean 的最终状态，可以自定义修改 beanDefinition.setXX
	}

	/**
	 * 通过 AbstractBeanDefinition 以及派生类创建 BeanDefinition
	 */
	private static void createByAbstractBeanDefinition() {
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		// 设置 Bean 类型
		genericBeanDefinition.setBeanClass(User.class);
		// 通过 MutablePropertyValues 批量操作属性
		MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
		//mutablePropertyValues.addPropertyValue("id", 1);
		//mutablePropertyValues.addPropertyValue("name", "小张");
		mutablePropertyValues
				.add("id", 1)
				.add("name", "小张");

		// 通过 setPropertyValues 批量操作属性
		genericBeanDefinition.setPropertyValues(mutablePropertyValues);
	}
}
