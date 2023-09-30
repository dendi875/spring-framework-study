package com.zq.spring.bean.factory;

import com.zq.spring.ioc.overview.domain.User;

/**
 * {@link com.zq.spring.ioc.overview.domain.User} 工厂类
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-04 20:29:26
 */
public interface UserFactory {

	default User createUser() {
		return User.createUser();
	}
}
