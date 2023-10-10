package com.zq.bean.lifecycle;

import com.zq.spring.ioc.overview.domain.User;

/**
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-10 18:54:41
 */
public class UserHolder {
	private final User user;

	public UserHolder(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				'}';
	}
}
