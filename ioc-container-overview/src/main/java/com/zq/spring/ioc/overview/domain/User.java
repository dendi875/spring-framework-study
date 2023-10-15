package com.zq.spring.ioc.overview.domain;

import com.zq.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

public class User implements BeanNameAware {

	private Long id;

	private String name;

	private City city;

	private City[] workCities;

	private List<City> lifeCities;

	private Resource configFileLocation;

	private Company company;

	/**
	 * 当前 Bean名称
	 */
	private transient String beanName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Resource getConfigFileLocation() {
		return configFileLocation;
	}

	public void setConfigFileLocation(Resource configFileLocation) {
		this.configFileLocation = configFileLocation;
	}

	public City[] getWorkCities() {
		return workCities;
	}

	public void setWorkCities(City[] workCities) {
		this.workCities = workCities;
	}

	public List<City> getLifeCities() {
		return lifeCities;
	}

	public void setLifeCities(List<City> lifeCities) {
		this.lifeCities = lifeCities;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", city=" + city +
				", workCities=" + Arrays.toString(workCities) +
				", lifeCities=" + lifeCities +
				", configFileLocation=" + configFileLocation +
				", company=" + company +
				", beanName='" + beanName + '\'' +
				'}';
	}

	public static User createUser() {
		User user = new User();
		user.setId(1L);
		user.setName("小张");
		return  user;
	}

	@PostConstruct
	public void init() {
		System.out.println("User Bean [" + beanName + "] 初始化...");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("User Bean [" + beanName + "] 销毁中...");
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}


}
