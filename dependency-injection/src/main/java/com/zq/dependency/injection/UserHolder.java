package com.zq.dependency.injection;

import com.zq.spring.ioc.overview.domain.User;

/**
 *
 * {@link com.zq.spring.ioc.overview.domain.User} 的 Holder (管理) 类
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-30 21:09:26
 */
public class UserHolder {

	private User user;

	public UserHolder() {
	}

	public UserHolder(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				'}';
	}
}
