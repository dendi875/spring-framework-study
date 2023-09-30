package com.zq.spring.ioc.overview.domain;

import com.zq.spring.ioc.overview.annotation.Super;

/**
 * 超级用户
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-08-31 16:22:03
 */
@Super
public class SuperUser extends User {

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "SuperUser{" +
				"address='" + address + '\'' +
				"} " + super.toString();
	}
}
