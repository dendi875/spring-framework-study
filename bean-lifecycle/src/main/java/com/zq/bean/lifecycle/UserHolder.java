package com.zq.bean.lifecycle;

import com.zq.spring.ioc.overview.domain.User;

/**
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-10 18:54:41
 */
public class UserHolder {
	private final User user;

	private Integer number;

	private String description;

	public UserHolder(User user) {
		this.user = user;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				", number=" + number +
				", description='" + description + '\'' +
				'}';
	}
}
