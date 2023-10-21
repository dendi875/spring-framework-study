package com.zq.questions;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * 教室类
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-21 17:11:24
 */
public class ClassRoom {

	private String name;

	@Autowired
	private Collection<Student> students;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ClassRoom{" +
				"name='" + name + '\'' +
				", students=" + students +
				'}';
	}
}
