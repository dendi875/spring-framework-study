package com.zq.bean.lifecycle;

import com.zq.spring.ioc.overview.domain.SuperUser;
import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * 包含 Spring Bean 生命周期：
 * 1. 实例化前
 * 2. 实例化后
 * 3. 属性赋值前
 * 4. 初始化前阶段
 * 5. 初始化后阶段
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-25 15:33:37
 */
class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
			// 把配置完成的 superUser Bean 覆盖掉
			return new SuperUser();
		}
		return null; // 保持 Spring IoC 容器的实例化操作，也就是保持即有状态不变
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
			User user = (User) bean;
			user.setId(2L);
			user.setName("zq2");
			// "user" 对象不允许属性赋值（填入）（配置元信息 -> 属性值）
			return false;
		}
		return true;
	}

	// 在实例化之后和赋值中间还有一个阶段，也就是在赋值前阶段有个回调，我们称之为属性赋值前的一个生命周期
	// user 是跳过 Bean 属性赋值（填入）
	// superUser 也是完全跳过 Bean 实例化（Bean 属性赋值（填入））
	// userHolder 对属性值进行拦截修改
	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
			throws BeansException {
		// 对 "userHolder" Bean 进行拦截
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
			// 假设 <property name="number" value="1" /> 配置的话，那么在 PropertyValues 就包含一个 PropertyValue(number=1)

			final MutablePropertyValues propertyValues;

			if (pvs instanceof MutablePropertyValues) {
				propertyValues = (MutablePropertyValues) pvs;
			} else {
				propertyValues = new MutablePropertyValues();
			}

			// 等价于 <property name="number" value="1" />
			propertyValues.addPropertyValue("number", "1");
			// 原始配置 <property name="description" value="The user holder" />

			// 如果存在 "description" 属性配置的话
			if (propertyValues.contains("description")) {
				// PropertyValue value 是不可变的
//                    PropertyValue propertyValue = propertyValues.getPropertyValue("description");
				propertyValues.removePropertyValue("description");
				propertyValues.addPropertyValue("description", "The user holder V2");
			}

			return propertyValues;
		}
		return null;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
			UserHolder userHolder = (UserHolder) bean;
			// UserHolder description = "The user holder V2"
			userHolder.setDescription("The user holder V3");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
			UserHolder userHolder = (UserHolder) bean;
			// UserHolder description = "The user holder V2"
			userHolder.setDescription("The user holder V7");
		}
		return bean;
	}
}
