package com.zq.spring.ioc.overview.repository;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * 用户信息仓库
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-08-31 16:53:13
 */
public class UserRepository {

	private Collection<User> users; // 自定义 Bean

	private BeanFactory beanFactory; // 容器内建的 Bean 对象

	private ObjectFactory<ApplicationContext> objectFactory;

	public ObjectFactory<ApplicationContext> getObjectFactory() {
		return objectFactory;
	}

	public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
		this.objectFactory = objectFactory;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
}
