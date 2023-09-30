package com.zq.spring.bean.factory;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link com.zq.spring.ioc.overview.domain.User} Bean 的 {@link FactoryBean} 实现
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-04 20:35:42
 */
public class UserFactoryBean implements FactoryBean {

	@Override
	public Object getObject() throws Exception {
		return User.createUser();
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}
}
