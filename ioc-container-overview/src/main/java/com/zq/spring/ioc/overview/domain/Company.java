package com.zq.spring.ioc.overview.domain;

/**
 * 
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-15 16:20:11
 */
public class Company {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company{" +
				"name='" + name + '\'' +
				'}';
	}
}
