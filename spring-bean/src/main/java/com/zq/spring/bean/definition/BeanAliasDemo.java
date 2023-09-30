package com.zq.spring.bean.definition;

import com.zq.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 34丨Spring Bean的别名：为什么命名Bean还需要别名？.mp4
 *
 * @author <quanzhang875@gmail.com>
 * @since  2023-09-04 15:42:04
 */
public class BeanAliasDemo {
	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

		User user = beanFactory.getBean("user", User.class);
		// 通过别名获取 User 的 Bean
		User zqUser = beanFactory.getBean("zqUser", User.class);
		System.out.println("zqUser Bean 是否与 user Bean 相同：" + (user == zqUser));
	}
}
