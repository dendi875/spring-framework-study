package com.zq.bean.lifecycle;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 *
 * Spring Bean Aware接口回调阶段
 * • Spring Aware 接口
 * 		• BeanNameAware
 * 		• BeanClassLoaderAware
 * 		• BeanFactoryAware
 *
 * 		• EnvironmentAware
 * 		• EmbeddedValueResolverAware
 * 		• ResourceLoaderAware
 * 		• ApplicationEventPublisherAware
 * 		• MessageSourceAware
 * 		• ApplicationContextAware
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-10 18:54:41
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
		EnvironmentAware {
	private final User user;

	private Integer number;

	private String description;

	private String name;

	private BeanFactory beanFactory;

	private ClassLoader classLoader;

	private Environment environment;

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
				", name='" + name + '\'' +
				'}';
	}


	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public void setBeanName(String name) {
		this.name = name;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
