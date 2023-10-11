package com.zq.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 *
 *{@link DestructionAwareBeanPostProcessor } 实现
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-11 10:29:05
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

	@Override
	public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
		if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
			UserHolder userHolder = (UserHolder) bean;
			// afterSingletonsInstantiated() = The user holder V8
			// UserHolder description = "The user holder V8"
			userHolder.setDescription("The user holder V9");
			System.out.println("postProcessBeforeDestruction() : " + userHolder.getDescription());
		}
	}
}
