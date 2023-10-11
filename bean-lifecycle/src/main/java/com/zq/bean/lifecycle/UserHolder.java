package com.zq.bean.lifecycle;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 包含：Spring Bean Aware接口回调、初始化中、初始化完成、销毁中、GC 阶段的示例
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
		EnvironmentAware,
		InitializingBean, // 初始化时阶段
		SmartInitializingSingleton, // 初始化完成阶段
		DisposableBean // 销毁时阶段
{
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

	///////////////////// Spring Bean Aware接口回调阶段 //////////////////
	@Override
	public void setBeanName(String name) {
		this.name = name;
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	///////////////////// Spring Bean 初始化中阶段 //////////////////
	@PostConstruct
	public void initPostConstruct() {
		// MyInstantiationAwareBeanPostProcessor#postProcessBeforeInitialization v3 => initPostConstruct v4
		this.description = "The user holder V4";
		System.out.println("initPostConstruct() = " + description);
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		this.description = "The user holder V5";
		System.out.println("afterPropertiesSet() = " + description);
	}

	/**
	 * 自定义实现初始化方法
	 */
	public void init() {
		this.description = "The user holder V6";
		System.out.println("init() = " + description);
	}

	///////////////////// Spring Bean 初始化完成阶段 //////////////////
	@Override
	public void afterSingletonsInstantiated() {
		this.description = "The user holder V8";
		System.out.println("afterSingletonsInstantiated() = " + description);
	}

	///////////////////// Spring Bean 销毁中阶段 //////////////////
	@PreDestroy
	public void preDestroy() {
		this.description = "The user holder V10";
		System.out.println("preDestroy() = " + description);
	}


	@Override
	public void destroy() throws Exception {
		this.description = "The user holder V11";
		System.out.println("destroy() = " + description);
	}

	public void doDestroy() throws Exception {
		this.description = "The user holder V12";
		System.out.println("doDestroy() = " + description);
	}

	///////////////////// Spring Bean GC 阶段 /////////////////////
	protected void finalize() throws Throwable {
		System.out.println("The UserHolder is finalized...");
	}
}
